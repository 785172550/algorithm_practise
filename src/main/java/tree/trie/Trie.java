package tree.trie;

import java.util.TreeMap;

public class Trie {

  private class Node {

    public boolean isWord;
    public TreeMap<Character, Node> next;

    private Node(boolean isWord) {
      this.isWord = isWord;
      next = new TreeMap<>();
    }

    public Node() {
      this(false);
    }
  }

  private Node root;
  private int size;

  public Trie() {
    this.root = new Node();
    this.size = 0;
  }

  public void add(String word) {

    Node cur = root;

    char[] chars = word.toCharArray();
    for (char c : chars) {
      if (cur.next.get(c) == null) {
        cur.next.put(c, new Node());
      }
      cur = cur.next.get(c);
    }

    if (!cur.isWord) {
      cur.isWord = true;
      size++;
    }
  }

  public boolean contains(String word) {
    return false;
  }

}
