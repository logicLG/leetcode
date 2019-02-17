package trie;

import java.util.*;

/**
 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

 若无答案，则返回空字符串。

 示例 1:

 输入:
 words = ["w","wo","wor","worl", "world"]
 输出: "world"
 解释:
 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 示例 2:

 输入:
 words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 输出: "apple"
 解释:
 "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 注意:

 所有输入的字符串都只包含小写字母。
 words数组长度范围为[1,1000]。
 words[i]的长度范围为[1,30]。
 **/

class Trie2 {
    Map<Character, Trie2> next = new HashMap<>();

    void insert(String words) {
        insert(this.next,words);
    }

    void insert(Map<Character, Trie2> next, String words) {
        words = words.toLowerCase();////转化为小写
        char[] chrs = words.toCharArray();
        for (int i = 0, length = chrs.length; i < length; i++) {
            if (!next.containsKey(chrs[i])) {
                next.put(chrs[i], new Trie2());
            }
            next = next.get(chrs[i]).next;
        }
    }

    boolean contains(String words) {
        return contains(this.next, words);
    }

    boolean contains(Map<Character, Trie2> next, String words) {
        words = words.toLowerCase();////转化为小写
        char[] chrs = words.toCharArray();
        for (int i = 0, length = chrs.length; i < length; i++) {
            if (!next.containsKey(chrs[i])) {
                return false;
            }
            next = next.get(chrs[i]).next;
        }
        return true;
    }
}
public class LargestWords {
    public String longestWord(String[] words) {
        Trie2 tire = new Trie2();
        Arrays.sort(words);
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (words.length != 1) {
                if (tire.contains(word.substring(0, word.length() - 1))) {
                    tire.insert(word);
                    list.add(word);
                }
            } else {
                tire.insert(word);
                list.add(word);
            }
        }
        int minLength = Integer.MIN_VALUE;
        String result = "";
        for (String s : list) {
            if (s.length() > minLength) {
                minLength = s.length();
                result = s;
            } else if (s.length() == minLength && s.compareTo(result) < 0) {
                minLength = s.length();
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"a", "app", "banana", "appl", "ap", "apply", "apple"};
        LargestWords l = new LargestWords();
        l.longestWord(words);
    }
}
