package multithreading.synchronization;

/**
 * Study Nodes
 * ------------
 * 内部锁和条件存在一些局限，包括：
 * 1. 不能中断一个正在试图获得锁的线程
 *    这句的意思是 正在试图获得锁的线程
 *    由于阻塞无法进行 isInterrupted 判断 所以无法中断
 *    而非interrupted无法被置位为true
 *    且此线程不会因此抛出 InterruptedException 异常
 * 2. 试图获得锁时不能设定超时
 * 3. 每个锁仅有单一的条件，可能是不够的
 */
public class Synchronized {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            try {
                // 等待main获得锁
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " try to get lock");
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get lock");
                System.out.println("interrupted = " + Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " get lock");
            // 等待thread试图获得锁
            Thread.sleep(200);
            // 中断一个正在试图获得锁的线程
            thread.interrupt();
            System.out.println(Thread.currentThread().getName() + " will lose lock");
        }
    }
}
