package trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定多个 words，words[i] 的权重为 i 。
 * <p>
 * 设计一个类 WordFilter 实现函数WordFilter.f(String prefix, String suffix)。这个函数将返回具有前缀 prefix 和后缀suffix 的词的最大权重。如果没有这样的词，返回 -1。
 * <p>
 * 例子:
 * <p>
 * 输入:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // 返回 0
 * WordFilter.f("b", "") // 返回 -1
 **/
public class WordFilter {
    trie3 preTrie;
    trie3 sufTrie;
    int length;

    public WordFilter(String[] words) {
        preTrie = new trie3();
        sufTrie = new trie3();
        for (int i = 0; i < words.length; i++) {
            preTrie.insert(words[i], i);
            sufTrie.insert(WordFilter.inverse(words[i]), i);
        }
        length = words.length - 1;
    }

    public int f(String prefix, String suffix) {
        if (prefix.equals("") && !suffix.equals("")) {
            List<Integer> suffixIndexs = sufTrie.search(suffix, false);
            if (suffixIndexs == null) return -1;
            return Collections.max(suffixIndexs);
        }
        if (prefix.equals("") && suffix.equals("")) {
            return length;
        }
        if (!prefix.equals("") && suffix.equals("")) {
            List<Integer> prefixIndexs = preTrie.search(prefix, true);
            if (prefixIndexs == null) return -1;
            return Collections.max(prefixIndexs);
        }
        List<Integer> prefixIndexs = preTrie.search(prefix, true);
        List<Integer> suffixIndexs = sufTrie.search(suffix, false);
        if (prefixIndexs == null || suffixIndexs == null) return -1;
        int result = Integer.MIN_VALUE;
        for (Integer i : prefixIndexs) {
            for (Integer j : suffixIndexs) {
                if (i == j && i > result)
                    result = i;
            }
        }
        if (result == Integer.MIN_VALUE) return -1;
        return result;

    }

    public static String inverse(String string) {
        if (string == null || string.length() == 0) return string;
        int length = string.length();
        char[] array = string.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            array[i] = string.charAt(length - 1 - i);
            array[length - 1 - i] = string.charAt(i);
        }
        return new String(array);
    }
}

class trie3 {
    trie3[] children = new trie3[26];
    List<Integer> indexs = new ArrayList<>();//该前缀包含的单词index

    void insert(String word, int index) {
        if (word.length() == 0) return;
        trie3 root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            root.indexs.add(index);
            if (root.children[c - 'a'] == null) {
                root.children[c - 'a'] = new trie3();
            }
            root = root.children[c - 'a'];
        }
        root.indexs.add(index);
    }

    public List<Integer> search(String prefix, boolean flag) {
        trie3 root = this;
        char[] chars;
        if (flag)
            chars = prefix.toCharArray();
        else chars = WordFilter.inverse(prefix).toCharArray();
        int i = 0;
        for (; i < chars.length; i++) {
            if (root.children[chars[i] - 'a'] != null)
                root = root.children[chars[i] - 'a'];
            else break;
        }
        if (i == chars.length)
            return root.indexs;
        else return null;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"pop"};
        WordFilter w = new WordFilter(a);
        System.out.println(w.f("pop", ""));
    }
}