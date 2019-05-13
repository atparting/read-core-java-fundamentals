package multithreading.synchronization;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Study Notes
 * ------------
 * Lock readLock()
 * 得到一个可以被多个读操作共用的读锁，但会排斥所有写操作
 * Lock writeLock()
 * 得到一个写锁，排斥所有其他的读操作和写操作
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Thread read = new Thread(() -> {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " read start");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " read end");
            readLock.unlock();
        });
        Thread read1 = new Thread(() -> {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " read start");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " read end");
            readLock.unlock();
        });
        Thread write = new Thread(() -> {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " write start");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " write end");
            writeLock.unlock();
        });
        Thread write1 = new Thread(() -> {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " write start");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " write end");
            writeLock.unlock();
        });
        read.start();
        read1.start();
        write.start();
        write1.start();
    }
}
