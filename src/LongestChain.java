/*
longestchain
类似word ladder，对于一个单词删掉任何一个字母，如果新单词出现在给的词典里 那么就形成一个 chain： old word -> new word -> newer word,
求最长长度(return int) 比如给vector<string> w = {a,ba,bca,bda,bdca} 最长是4： bdca->bda->ba->a;
思路: 建一个map<长度，set<string>> 根据长度把输入的字典放到set里，然后从最长的长度所在的map[长度] 开始枚举每个单词并缩减然后进行recursive call，
比如bdca 那么就可能缩成dca,bca,bda 然后去map[3]里找，类推直到 word.size()==1 或找不到， 放个全局int去记最大长度。
注意找到后从字典里删除和word ladder一样 否则只能过4个case 会超时。
 */

import java.util.*;

public class LongestChain {
    int maxLength = 1;

    public int longestChainLength(String[] words) {
        if (words.length < 1) return 0;
        Map<Integer, Set<String>> lenDict = new HashMap<>();
        for (String str : words) {
            int len = str.length();
            if (lenDict.containsKey(len)) {
                lenDict.get(len).add(str);
            } else {
                Set<String> set = new HashSet<>();
                set.add(str);
                lenDict.put(len, set);
            }
        }
        List<Integer> keyList = new ArrayList<>(lenDict.keySet());
        Collections.sort(keyList);
        int minLength = keyList.get(0);
        for (int i = keyList.size() - 1; i > 0; i--) {  // No need to iterate on strings with shortest length
            int len = keyList.get(i);
            for (String str : lenDict.get(len)) {
                for (int j = 0; j < str.length(); j++) {
                    findLongestChain(lenDict, str, 1, minLength);
                }
            }
        }
        return maxLength;
    }

    private void findLongestChain(Map<Integer, Set<String>> lenDict, String word, int length, int minLength) {
        if (length > maxLength) maxLength = length;
        if (word.length() < minLength) return;
        for (int i = 0; i < word.length(); i++) {
            String next = word.substring(0, i) + word.substring(i + 1);
            if (lenDict.get(next.length()) != null && lenDict.get(next.length()).contains(next)) {
                System.out.println(word + "->" + next);
                findLongestChain(lenDict, next, length + 1, minLength);
                lenDict.get(next.length()).remove(next);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"sadfjlskdf", "sdfjsdlkfkl", "a", "ba", "bca", "bda", "bbca", "bkkca"};
        LongestChain sol = new LongestChain();
        System.out.println(sol.longestChainLength(words));
    }
}
