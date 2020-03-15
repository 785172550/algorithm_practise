package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlideWindowByDeque {
  public static void main(String[] args) {
//    List<Integer> data2 = arrayToCollection(new int[]{2, 3, 4, 2, 6, 2, 5, 1});
    List<Integer> data2 = arrayToCollection(new int[]{8, 7, 6, 5, 4, 3, 2, 1});
    System.out.println(data2 + "," + maxInWindows(data2, 3));
  }

  /*
  一个数组 窗口大小3 求每个窗口的最大值序列

  {3,2,4,2,6,2,5,1} size=3
  所以一共六个 窗口 {4,4,6,6,6,5}

  思路:
  一个deque来保存窗口中最大值:
  1. 队首 就是当前最大值
  2. 入队一个 addLast(), 且前面有比当前值小的已经不可能是最大值了 removeLast
  3. 队首的index + size - 1 < 当前index时，已经滑过，removeFirst

   */

  private static List<Integer> maxInWindows(List<Integer> data, int size) {
    // 结果收集
    List<Integer> windowMax = new LinkedList<>();

    // 条件检查
    if (data == null || size < 1 || data.size() < 1) {
      return windowMax;
    }

    // 滑动窗口 下标索引
    Deque<Integer> idx = new LinkedList<>();

    // 窗口没有满 初始化 deque
    for (int i = 0; i < size && i < data.size(); i++) {
      // 如果索引对应的值比之前存储的索引值对应的值大或者相等，就删除之前存储的值
      while (!idx.isEmpty() && data.get(i) >= data.get(idx.getLast())) {
        idx.removeLast();
      }
      //  添加索引
      idx.addLast(i);
    }

    // 窗口已经被填满了
    for (int i = size; i < data.size(); i++) {
      // 第一个窗口的最大值保存
      windowMax.add(data.get(idx.getFirst()));
      // 如果索引对应的值比之前存储的索引值对应的值大或者相等，就删除之前存储的值
      while (!idx.isEmpty() && data.get(i) >= data.get(idx.getLast())) {
        idx.removeLast();
      }
      //滑动窗口的大小为 [i - size + 1...i] 得出： idx.first >= i - size + 1
      if (!idx.isEmpty() && idx.getFirst() < (i - size + 1)) {
        idx.removeFirst();
      }
      // 可能的最大的下标索引入队
      idx.addLast(i);
    }

    // 最后一个窗口最大值入队
    windowMax.add(data.get(idx.getFirst()));
    return windowMax;
  }

  private static List<Integer> arrayToCollection(int[] array) {
    List<Integer> result = new LinkedList<>();
    if (array != null) {
      for (int i : array) {
        result.add(i);
      }
    }
    return result;
  }
}
