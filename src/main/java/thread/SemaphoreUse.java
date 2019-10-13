package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreUse {

  public static void main(String[] args) {
    // 交替print 30次a，15次b，10次c
    print3();
    // simplified with semaphore
    enhance();
  }


  private static void print3() {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    AtomicBoolean t1Run = new AtomicBoolean(false);
    AtomicBoolean t2Run = new AtomicBoolean(false);
    AtomicBoolean t3Run = new AtomicBoolean(true);

    Thread t1 = new Thread(() -> {
      int count = 0;
      lock.lock();
      while (count < 30) {
        if (t3Run.get()) {
          System.out.println("a" + count);
          count++;
          t1Run.set(true);
          if (count < 10)
            t3Run.set(false);
          condition1.signalAll();
        }
        try {
          if (count <= 15)
            condition1.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    });

    Thread t2 = new Thread(() -> {
      int count = 0;
      lock.lock();
      while (count < 15) {
        if (t1Run.get()) {
          System.out.println("b" + count);
          count++;
          t2Run.set(true);
          t1Run.set(true);
          condition1.signalAll();
        }
        try {
          condition1.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    });

    Thread t3 = new Thread(() -> {
      int count = 0;
      lock.lock();
      while (count < 10) {
        if (t2Run.get()) {
          System.out.println("c" + count);
          count++;
          t3Run.set(true);
          t2Run.set(false);
          condition1.signalAll();
        }
        try {
          condition1.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      lock.unlock();
    });

    t1.start();
    t2.start();
    t3.start();
  }


  public static void enhance() {
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(1);

    Thread t1 = new Thread(() -> {
      int count = 0;
      while (count < 30) {
        try {
          if (count < 10)
            s3.acquire(1);
          else if (count <= 15)
            s2.acquire(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("a " + count);
        count++;
        s1.release(1);
      }
    });

    Thread t2 = new Thread(() -> {
      int count = 0;
      while (count < 15) {
        try {
          s1.acquire(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("b " + count);
        count++;
        s2.release(1);
      }
    });

    Thread t3 = new Thread(() -> {
      int count = 0;
      while (count < 10) {
        try {
          s2.acquire(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("c " + count);
        count++;
        s3.release(1);
      }
      s2.release(1);
    });

    t1.start();
    t2.start();
    t3.start();

  }
}
