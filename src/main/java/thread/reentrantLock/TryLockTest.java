package thread.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) { // 等待1秒
                Thread.sleep(2000);  //休眠2秒
            } else {
                System.err.println(Thread.currentThread().getName() + "获取锁失败！");
            }
        } catch (Exception e) {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLockTest test = new TryLockTest();
        Thread t1 = new Thread(test); 
        t1.setName("线程1");
        Thread t2 = new Thread(test); 
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}
/**
 * 运行结果:
 * 线程2获取锁失败！
 */ 
