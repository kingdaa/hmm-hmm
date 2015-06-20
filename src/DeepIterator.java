import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 设计一个deepIterator。
 * 大意如下，你有一个nestedList的数据结构，里面的每个element可能是一个int，也可能是另外一个nestedList，要求设计一个iterator，做到如下的功能
 * {1,{2,5, {7}}} -> 1,2,5,7
 * 简单的说，就是每次call iterator的next，要把里面的下一个int element返回
 * 要注意的cornor case，比如这种{{{}}},或者{{},{},{}}
 */
class NestedList {
    ArrayList<Object> list;

    public NestedList() {
        list = new ArrayList<Object>();
    }

    public NestedList(int a) {
        list = new ArrayList<Object>();
        list.add(a);
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void add(int n) {
        list.add(n);
    }

    public void add(NestedList nl) {
        list.add(nl);
    }

    public Object get(int n) {
        return list.get(n);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void print() {
        System.out.print("{");
        for (Object obj : list) {
            if (obj instanceof Integer) {
                System.out.print(obj + " ");
            } else if (obj instanceof NestedList) {
                ((NestedList) obj).print();
            }
        }
        System.out.print("}");
    }
}

class DeepIter implements Iterator<Integer> {
    private final NestedList _list;
    private Stack<NestedList> listStack;
    private Stack<Integer> curStack;

    public DeepIter(NestedList list) {
        _list = list;
        listStack = new Stack<NestedList>();
        listStack.push(_list);
        curStack = new Stack<Integer>();
        curStack.push(0);
    }

    @Override
    public boolean hasNext() {
        return !listStack.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        NestedList curList = listStack.peek();
        int curIndex = curStack.peek();
        if (curIndex == curList.size()) {
            curStack.pop();
            listStack.pop();
            return next();
        }
        while (curList.get(curIndex) instanceof NestedList) {
            if (!((NestedList) curList.get(curStack.peek())).isEmpty()) {
                listStack.push((NestedList) curList.get(curStack.peek()));
                curStack.set(curStack.size() - 1, curStack.peek() + 1);
                curStack.push(0);
            }
            curList = listStack.peek();
            curIndex = curStack.peek();
        }
        Integer res = (Integer) curList.get(curIndex);
        if (curIndex + 1 >= curList.size()) {
            curStack.pop();
            listStack.pop();
        } else {
            //Increase the current list index by 1
            curStack.set(curStack.size() - 1, curIndex + 1);
        }
        return res;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

public class DeepIterator {
    public static void main(String[] args) {
        NestedList n1 = new NestedList();
        n1.add(1);
        NestedList n2 = new NestedList();
        n2.add(2);
        n2.add(5);
        NestedList n3 = new NestedList(7);
        n2.add(n3);
        n1.add(n2);
        n1.print();
        System.out.println();
        Iterator<Integer> iter = new DeepIter(n1);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }


        NestedList nn1 = new NestedList();
        NestedList nn2 = new NestedList(1);
        nn1.add(nn2);
        NestedList nn3 = new NestedList(7);
        nn1.add(nn3);
        NestedList nn4 = new NestedList();
        nn4.add(8);
        nn4.add(9);
        nn4.add(10);
        nn4.add(11);
        nn4.add(12);
        nn1.add(nn4);

        nn1.print();
        System.out.println();
        Iterator<Integer> iter2 = new DeepIter(nn1);
        while (iter2.hasNext()) {
            System.out.println(iter2.next());
        }
    }
}
