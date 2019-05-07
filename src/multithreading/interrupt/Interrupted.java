package multithreading.interrupt;


/**
 * Study Notes
 * ------------
 * 1. boolean isInterrupted
 *    测试线程是否被终止
 *    不改变线程的中断状态
 * 2. static boolean interrupted
 *    测试当前线程是否被中断
 *    会将当前线程的中断状态重置为false
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean one = Thread.currentThread().isInterrupted();
                System.out.println("one = " + one);
                boolean two = Thread.interrupted();
                System.out.println("two = " + two);
                boolean three = Thread.currentThread().isInterrupted();
                System.out.println("three = " + three);
                if (two)
                    break;
            }
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        System.out.println("call interrupt");
    }
}
