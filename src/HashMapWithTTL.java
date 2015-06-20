import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Design and implement a HashMap that suppports concurrent read & write.
 * In the HashMap, each entry has an TTL (Time To Live), the entry needs to delete itself when it expires.
 */

public class HashMapWithTTL<K, V> implements Map<K, V> {
    private final long DEFAULT_TTL = 10000;
    private final ConcurrentHashMap<K, V> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, Long> timestamps = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, Long> entryTTL = new ConcurrentHashMap<>();
//    private final long ttl;

    public HashMapWithTTL() {

    }

//    public HashMapWithTTL(TimeUnit ttlUnit, long ttlValue) {
//        this.ttl = ttlUnit.toNanos(ttlValue);
//    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        V value = store.get(key);
        if (value == null) return false;
        else {
            if (isKeyExpired(key)) {
                remove(key);
                return false;
            } else return true;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    @Override
    public V get(Object key) {
        V value = store.get(key);
        if (value == null) return null;
        else {
            if (isKeyExpired(key)) {
                remove(key);
                return null;
            } else return value;
        }
    }

    private boolean isKeyExpired(Object key) {
        return System.nanoTime() - timestamps.get(key) > entryTTL.get(key);
    }

    private void clearAllExpiredEntries() {
        for (K key : store.keySet()) {
            this.get(key);
        }
    }

    public V put(K key, V value, long ttl) {
        store.put(key, value);
        entryTTL.put(key, ttl);
        timestamps.put(key, System.nanoTime());
        return value;
    }

    @Override
    public V put(K key, V value) {
        return put(key, value, DEFAULT_TTL);
    }

    @Override
    public V remove(Object key) {
        timestamps.remove(key);
        entryTTL.remove(key);
        return store.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        timestamps.clear();
        entryTTL.clear();
        store.clear();
    }

    @Override
    public Set<K> keySet() {
        clearAllExpiredEntries();
        return Collections.unmodifiableSet(store.keySet());
    }

    @Override
    public Collection<V> values() {
        clearAllExpiredEntries();
        return Collections.unmodifiableCollection(store.values());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        clearAllExpiredEntries();
        return Collections.unmodifiableSet(store.entrySet());
    }

    @Override
    public String toString() {
        return store.toString() + "\n" + entryTTL.toString() + "\n" + timestamps.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        HashMapWithTTL<String, String> map = new HashMapWithTTL<>();
//        map.put("deming", "liveforever", TimeUnit.MILLISECONDS.toNanos(2000));
//        map.put("kaizi", "livefor200ms", TimeUnit.MILLISECONDS.toNanos(200));
        MapWriter<String, String> mw1 = new MapWriter<>("deming", "liveforever", TimeUnit.MILLISECONDS.toNanos(2000), map);
        MapWriter<String, String> mw2 = new MapWriter<>("kaizi", "livefor900ms", TimeUnit.MILLISECONDS.toNanos(900), map);
        Thread t1 = new Thread(mw1);
        Thread t2 = new Thread(mw2);
        MapReader<String, String> mr1 = new MapReader<>("deming", map);
        MapReader<String, String> mr2 = new MapReader<>("kaizi", map);
        Thread t3 = new Thread(mr1);
        Thread t4 = new Thread(mr2);
        t1.start();
        t2.start();
        t3.start();
        t4.wait();
        Thread.sleep(900);
        t4.notify();
        t4.start();
//            System.out.println(map.get("deming"));
//            System.out.println(map.get("kaizi"));
//            Thread.sleep(200);
//            System.out.println(map.get("deming"));
//            System.out.println(map.get("kaizi"));
//            Thread.sleep(1800);
//            System.out.println(map.get("deming"));
//            System.out.println(map.get("kaizi"));
    }
}

class MapWriter<K, V> implements Runnable {
    private final K key;
    private final V value;
    private final long ttl;
    private HashMapWithTTL<K, V> map;

    public MapWriter(K key, V value, long ttl, HashMapWithTTL<K, V> map) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.map = map;
    }

    public void changeMap(HashMapWithTTL<K, V> map) {
        this.map = map;
    }

    @Override
    public void run() {
        if (map != null)
            map.put(key, value, ttl);
    }
}

class MapReader<K, V> implements Runnable {
    private final K key;
    private final HashMapWithTTL<K, V> map;

//    private V value;

    public MapReader(K key, HashMapWithTTL<K, V> map) {
        this.key = key;
        this.map = map;
    }

    public V getValue() {
        return map == null ? null : map.get(key);
    }

    @Override
    public void run() {
        System.out.println(this.getValue());
    }
}
