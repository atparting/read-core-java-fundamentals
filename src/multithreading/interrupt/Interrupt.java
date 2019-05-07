package multithreading.interrupt;

/**
 * Study Notes
 * ------------
 * 1. 没有可以强制终止线程的方法
 * 2. interrupt 方法可以用来请求终止线程
 *    线程的中断状态将被置位
 * 3. 首先调用 Thread.currentThread 获得当前线程
 *    然后调用 isInterrupted 方法 获得中断标志状态
 */
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("thread " + Thread.currentThread().getName() + " interrupt = " + false);
            }
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        System.out.println("thread " + thread.getName() + " interrupt = " + thread.isInterrupted());
    }
}
