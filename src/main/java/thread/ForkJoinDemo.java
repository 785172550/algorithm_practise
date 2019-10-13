package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinDemo {

  // 效果不是很明显， 需要注意CPU core数量和Fork出来task的对应
  public static void main(String[] args) {

//    ForkJoinDemo demo = new ForkJoinDemo();
    ForkJoinPool pool = ForkJoinPool.commonPool();
    long[] input = LongStream.range(1, 1000000).toArray();

    long start = System.currentTimeMillis();
    long total = 0;
    for (int i = 0; i < 999999; i++) {
      total += input[i];
    }
    long dur = System.currentTimeMillis() - start;
    System.out.println(dur + ":" + total);

    start = System.currentTimeMillis();
    Long res = pool.invoke(new SumTask(input, 0, 999999));
    dur = System.currentTimeMillis() - start;
    System.out.println(dur + " : " + res);
    //    System.out.println("-- " + res);

  }


}

class SumTask extends RecursiveTask<Long> {

  private long[] nums;
  private int left; // [0..left] 区间的数字
  private int right;// [left+1..right)

  SumTask(long[] nums, int left, int right) {
    this.nums = nums;
    this.left = left;
    this.right = right;
  }


  @Override protected Long compute() {
    if (right - left < 4000000) {
      long total = 0;
      for (int i = left; i < right; i++) {
        total += nums[i];
      }
//      System.out.println(String.format("thread: %s, sums: %s, left: %s, right: %s",
//        Thread.currentThread().getName(), total, left, right));
      return total;
    } else {
      int mid = left + (right - left) / 2;
      SumTask task1 = new SumTask(nums, left, mid);
      SumTask task2 = new SumTask(nums, mid + 1, right);
      task1.fork();
      task2.fork();
      return task1.join() + task2.join();
    }

  }
}