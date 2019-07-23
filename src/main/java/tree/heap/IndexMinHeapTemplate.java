package tree.heap;

import java.util.Arrays;

/**
 * 泛型索引堆
 */
public class IndexMinHeapTemplate<Item extends Comparable<Item>> {

  Item data[];
  int index[]; // index[0] = 10 表示 data[10] 的数据应该排在 第 0 的位置
  int reverse[]; // 反向索引 reverse[i] = x 表示索引i在x的位置
  int count;
  int capacity;

  public IndexMinHeapTemplate(int capacity) {
    this.capacity = capacity;
    data = (Item[]) new Comparable[capacity];
    count = 0;

    index = new int[capacity];
    reverse = new int[capacity];
    Arrays.fill(reverse, -1);
  }

  public void insert(int i, Item item) {
    assert count + 1 <= capacity;

    data[i] = item;
    index[count] = i;
    reverse[i] = count; // 添加元素之后， index[count]添加进堆尾， 所以reverse的index[count]值为 count
    shiftUp(count);

    count++;
  }

  public Item extractMin() {
    Item res = data[index[0]];
    swapIndex(0, count - 1);
    reverse[index[count - 1]] = -1; // index[count-1]驱逐出堆，   所以 reverse的index[count-1]值为-1

    count--;
    shiftDown(0);
    return res;
  }

  // update element at data[i]
  public void update(int i, Item item) {

    if (reverse[i] == -1)
      return; // 关键步骤， 确定 data[i]在堆中，

    data[i] = item;
    int index = reverse[i];
    shiftUp(index);
    shiftDown(index);
  }

  private void shiftUp(int k) {
    while (k > 0 && data[index[(k - 1) / 2]].compareTo(data[index[k]]) > 0) {
      swapIndex((k - 1) / 2, k);

      // update reverse becasue of index array changed
      reverse[index[(k - 1) / 2]] = (k - 1) / 2;
      reverse[index[k]] = k;
      k = (k - 1) / 2;
    }
  }

  private void shiftDown(int k) {
    int half = count / 2;
    while (k < half) {
      int child = k * 2 + 1; // assume left is the least
      if (data[index[child]].compareTo(data[index[child + 1]]) > 0)
        child = child + 1;
      if (data[index[k]].compareTo(data[index[child]]) < 0)
        break; // 最小的 child > val满足堆的定义
      swapIndex(k, child);

      // update reverse becasue of index array changed
      reverse[index[k]] = k;
      reverse[index[child]] = child;
      k = child;
    }
  }

  private void swapIndex(int i, int j) {
    int t = index[i];
    index[i] = index[j];
    index[j] = t;

    reverse[t] = j;
    reverse[index[i]] = i;
  }


}
