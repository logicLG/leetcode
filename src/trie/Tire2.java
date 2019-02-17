package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 **/
class Trie {
    Map<Character, Trie> next = new HashMap<>();
    boolean isLeaf = false;

    /**
     * Initialize your data structure here.
     */
    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        insert(this.next, word);
    }

    void insert(Map<Character, Trie> next, String word) {
        word = word.toLowerCase();////转化为小写
        char[] chrs = word.toCharArray();
        for (int i = 0, length = chrs.length; i < length; i++) {
            if (!next.containsKey(chrs[i])) {
                next.put(chrs[i], new Trie());
            }
            if (i == chrs.length - 1)
                next.get(chrs[i]).isLeaf = true;
            next = next.get(chrs[i]).next;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return search(this.next, word);
    }

    public boolean search(Map<Character, Trie> next, String word) {
        word = word.toLowerCase();////转化为小写
        char[] chrs = word.toCharArray();
        for (int i = 0, length = chrs.length; i < length; i++) {
            if (!next.containsKey(chrs[i])) {
                return false;
            }
            if (i == chrs.length - 1 && next.get(chrs[i]).isLeaf) {
                return true;
            }
            next = next.get(chrs[i]).next;
        }
        return false;
    }

    boolean startsWith(String words) {
        return startsWith(this.next, words);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(Map<Character, Trie> next, String prefix) {
        prefix = prefix.toLowerCase();////转化为小写
        char[] chrs = prefix.toCharArray();
        for (int i = 0, length = chrs.length; i < length; i++) {
            if (!next.containsKey(chrs[i])) {
                return false;
            }
            next = next.get(chrs[i]).next;
        }
        return true;
    }
}
