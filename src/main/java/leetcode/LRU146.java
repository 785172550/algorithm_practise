package leetcode;

import java.util.HashMap;

public class LRU146 {
  class LRUCache {
    class Node {
      int key;
      int val;
      Node pre;
      Node post;

      Node(int key, int val) {
        this.key = key;
        this.val = val;
      }
    }

    HashMap<Integer, Node> map;
    int count = 0;
    int capacity;
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
      this.capacity = capacity;
      map = new HashMap<Integer, Node>(capacity);

      // init
      head.pre = null;
      tail.post = null;
      head.post = tail;
      tail.pre = head;
    }

    public int get(int key) {
      Node node = map.get(key);
      if (node == null) return -1;

      moveToHead(node);
      return node.val;
    }

    public void put(int key, int value) {
      Node cur = map.get(key);
      if (cur != null) {
        cur.val = value;
        moveToHead(cur);
        return;
      }

      if (count < capacity) {
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);

        count++;
      } else {
        // remove last node
        map.remove(tail.pre.key);
        tail.pre = tail.pre.pre;
        tail.pre.post = tail;

        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
      }
    }

    private void addToHead(Node node) {
      node.pre = head;
      node.post = head.post;
      head.post = node;
      node.post.pre = node;
    }

    private void moveToHead(Node node) {
      node.pre.post = node.post;
      node.post.pre = node.pre;

      node.pre = head;
      node.post = head.post;

      node.post.pre = node;
      head.post = node;
    }
  }

  public static void main(String[] args) {
    // ["LRUCache","get","put","get","put","put","get","get"]
    // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
    LRU146.LRUCache cache = new LRU146().new LRUCache(2);
    cache.get(2);
    cache.put(2, 6);
    cache.get(1);
    cache.put(1, 5);
    cache.put(1, 2);
    cache.get(1);
    int val = cache.get(2);
    System.out.println(val);
  }
}
