package com.wh.test.tree;

/** @author hw83770 */
public class MinHeap {
  int[] data;
  int count;
  int capacity;

  public MinHeap(int capacity) {
    this.capacity = capacity;
    data = new int[capacity];
    count = 0;
  }

  public int extractMin() {
    assert count > 0;

    int res = data[0];
    count--;
    swap(0, count); // index = count - 1
    shiftDown(0);
    return res;
  }

  public void insert(int k) {
    assert count + 1 <= capacity;
    data[count] = k; // index = count - 1
    count++;
    shiftUp(count - 1);
  }

  public int size() {
    return count;
  }

  // --------------------------
  // swap shift up and down
  private void shiftUp(int k) {
    int parent = (k - 1) / 2;
    while (k > 0 && data[parent] > data[k]) {
      swap(k, parent);
      k = parent;
      parent = (k - 1) / 2;
    }
  }

  private void shiftDown(int k) {
    int half = count / 2;
    //    int half = size >>> 1;     // loop while a non-leaf
    while (k < half) {
      int child = (k << 1) + 1; // assume left is the least
      int item = data[child];
      int right = child + 1;
      if (right < count && data[right] < data[child]) item = data[child = right];
      if (item >= data[k]) break; // 最小的 child > val满足堆的定义
      swap(k, child);
      k = child;
    }
  }

  // --------------------------

  // dont need to swap shift up and down, performance improvement when > 100000
  private void shiftUp2(int k) {
    int val = data[k];
    int parent = (k - 1) / 2;
    //    && data[parent] > val
    while (k > 0) {
      if (data[parent] < val) break;
      data[k] = data[parent];
      k = parent;
      parent = (k - 1) / 2;
    }
    data[k] = val;
  }

  private void shiftDown2(int k) {
    int half = count / 2;
    int val = data[k];
    //    int half = size >>> 1;     // loop while a non-leaf
    while (k < half) {
      int child = (k << 1) + 1; // assume left is the least
      int item = data[child];
      int right = child + 1;
      if (right < count && data[right] < data[child]) item = data[child = right];
      if (item >= val) break; // 最小的 child > val满足堆的定义
      data[k] = item; // 不满足堆的时候， 把最小的child值 拿给 parent
      k = child; // 迭代child
    }
    data[k] = val;
  }

  // 交换堆中索引为i和j的两个元素
  private void swap(int i, int j) {
    int t = data[i];
    data[i] = data[j];
    data[j] = t;
  }

  // 测试 MinHeap
  public static void main(String[] args) {

    MinHeap minHeap = new MinHeap(100000);
    int N = 100000; // 堆中元素个数
    int M = 100000; // 堆中元素取值范围[0, M)
    //    int[] hh = new int[] {0, 2, 0, 0, 3, 3, 4, 4, 5, 7};
    //    for (int i = 0; i < N; i++) minHeap.insert(hh[i]);
    long start = System.currentTimeMillis();

    for (int i = 0; i < N; i++) minHeap.insert((int) (Math.random() * M));
    int[] arr = new int[N];
    //    minHeap.data = new int[] {0, 1, 8, 7, 7, 9, 9, 9, 8, 8};
    //    minHeap.count = 10;
    
    // 将minheap中的数据逐渐使用extractMin取出来
    // 取出来的顺序应该是按照从小到大的顺序取出来的
    for (int i = 0; i < N; i++) {
      arr[i] = minHeap.extractMin();
//      System.out.print(arr[i] + " ");
    }
    //    System.out.println();

    System.out.println();
    System.out.println(System.currentTimeMillis() - start);
    // 确保arr数组是从小到大排列的
    //    for (int i = 1; i < N; i++) assert arr[i - 1] <= arr[i];
  }
}
