package com.wh.test.tree;

import java.util.Arrays;

/**
 * @author hw83770
 *     <p>索引堆
 */
public class IndexMinHeap {

  // 测试 IndexMaxHeap
  public static void main(String[] args) {

    int N = 1000000;
    IndexMinHeap indexMaxHeap = new IndexMinHeap(N);
    for (int i = 0; i < N; i++) indexMaxHeap.insert(i, (int) (Math.random() * N));
    indexMaxHeap.testIndexes();
  }

  int data[];
  int index[]; // index[0] = 10 表示 data[10] 的数据应该排在 第 0 的位置
  int reverse[]; // 反向索引 reverse[i] = x 表示索引i在x的位置
  int count;
  int capacity;

  /* data: [5, 5, 5, 2, 1, 9, 0, 1, 8, 6]
   * index: [6, 7, 4, 3, 0, 5, 2, 1, 8, 9]
   * reverse: [4, 7, 6, 3, 2, 5, 0, 1, 8, 9]
   *
   */
  public IndexMinHeap(int capacity) {
    this.capacity = capacity;
    data = new int[capacity];
    index = new int[capacity];
    reverse = new int[capacity];
    Arrays.fill(reverse, -1);
  }

  public void insert(int i, int item) {
    assert count + 1 <= capacity;

    data[i] = item;
    index[count] = i;
    reverse[i] = count; // 添加元素之后， index[count]添加进堆尾， 所以reverse的index[count]值为 count
    shiftUp(count);

    count++;
  }

  public int extractMin() {
    int res = data[index[0]];
    swapIndex(0, count - 1);
    reverse[index[count - 1]] = -1; // index[count-1]驱逐出堆，   所以 reverse的index[count-1]值为-1

    count--;
    shiftDown(0);
    return res;
  }

  // update element at data[i]
  public void update(int i, int item) {

    if (reverse[i] == -1) return; // 关键步骤， 确定 data[i]在堆中，

    data[i] = item;
    int index = reverse[i];
    shiftUp(index);
    shiftDown(index);
  }

  public void shiftUp(int k) {
    while (k > 0 && data[index[(k - 1) / 2]] > data[index[k]]) {
      swapIndex((k - 1) / 2, k);

      // update reverse becasue of index array changed
      reverse[index[(k - 1) / 2]] = (k - 1) / 2;
      reverse[index[k]] = k;
      k = (k - 1) / 2;
    }
  }

  public void shiftDown(int k) {
    int half = count / 2;
    while (k < half) {
      int child = k * 2 + 1; // assume left is the least
      if (data[index[child]] > data[index[child + 1]]) child = child + 1;
      if (data[index[k]] < data[index[child]]) break; // 最小的 child > val满足堆的定义
      swapIndex(k, child);
      
      // update reverse becasue of index array changed
      reverse[index[k]] = k;
      reverse[index[child]] = child;
      k = child;
    }
  }

  public void swapIndex(int i, int j) {
    int t = index[i];
    index[i] = index[j];
    index[j] = t;

    reverse[t] = j;
    reverse[index[i]] = i;
  }

  // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
  public boolean testIndexes() {

    int[] copyIndexes = new int[count];
    int[] copyReverseIndexes = new int[count];

    for (int i = 0; i < count; i++) {
      copyIndexes[i] = index[i];
      copyReverseIndexes[i] = reverse[i];
    }

    //    copyIndexes[0] = 0;
    //    copyReverseIndexes[0] = 0;
    Arrays.sort(copyIndexes);
    Arrays.sort(copyReverseIndexes);

    // 在对索引堆中的索引和反向索引进行排序后,
    // 两个数组都应该正好是1...count这count个索引
    boolean res = true;
    for (int i = 1; i < count; i++)
      if (copyIndexes[i - 1] + 1 != copyIndexes[i]
          || copyReverseIndexes[i - 1] + 1 != copyReverseIndexes[i]) {
        res = false;
        break;
      }

    if (!res) {
      System.out.println("Error!");
      return false;
    }

    return true;
  }
}
