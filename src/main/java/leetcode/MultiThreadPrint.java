package leetcode;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadPrint {
  public static void main(String[] args) throws InterruptedException {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    DelayQueue<Delayed> q;

    Thread t0 =
        new Thread(
            () -> {
              int even = 0;
              while (even < 4) {
                try {
                  lock.lock();
                  System.out.println("0:" + even);
                  even += 2;
                  condition.signal();
                  condition.await();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });
    t0.start();

    Thread t1 =
        new Thread(
            () -> {
              int odd = 1;
              while (odd < 4) {
                try {
                  lock.lock();
                  System.out.println("1:" + odd);
                  odd += 2;
                  condition.signal();
                  condition.await();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });
    t1.start();

    //    t0.join();
    //    lock.lock();
    //    condition.signal();
    //    lock.unlock();
  }
}
