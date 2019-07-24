package leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadPrint {

  public static void main(String[] args) throws InterruptedException {

    //    DelayQueue<Delayed> q;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    Thread t0 =
      new Thread(() -> {
          int even = 0;
          lock.lock();

          while (even < 100) {
            System.out.println("0:" + even);
            even += 2;
            condition.signal();
            try {
              condition.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          condition.signal();
          lock.unlock();
          System.out.println("t0 out");
        });
    t0.start();

    Thread t1 =
      new Thread(() -> {
          int odd = 1;
          lock.lock();
          while (odd < 100) {
            System.out.println("1:" + odd);
            odd += 2;
            condition.signal();
            try {
              condition.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          lock.unlock();
          System.out.println("t1 out");
        });
    t1.start();
  }
}
