package multithreading.status;

/**
 * Study Notes
 * ------------
 * 线程的6种状态
 * - New（新创建）
 * - Runnable（可运行）
 * - Blocked（被阻塞）
 * - Waiting（等待）
 * - Timed waiting（计时等待）
 * - Terminated（被终止）
 */
public class Status {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个对象锁
        Object lock = new Object();
        /*
        新创建线程(New)
        还没有开始运行线程中的代码
         */
        Thread thread = new Thread(() -> {
            /*
            被阻塞线程和等待状态
            当线程处于被阻塞或等待状态时 它暂时不活动 不运行任何代码且消耗最少的资源
             */
            synchronized (lock) {
                try {
                    /*
                    等待状态(Waiting)
                    等待主线程持有lock
                    当一个线程等待另一个线程通知调度器一个条件时 它自己进入等待状态
                    在调用Object.wait方法或Thread.join方法
                    或者是等待java.util.concurrent库中的Lock或Condition时 就会出现这种情况
                    实际上 被阻塞状态和等待状态是有很大不同的
                     */
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*
                阻塞状态(Blocked)
                当一个线程试图获取一个内部的对象锁(而不是java.util.concurrent库中的锁) 而该锁被其他线程持有
                当所有其他线程释放该锁 并且线程调度器允许本线程持有它的时候 该线程将变成非阻塞状态
                 */
                System.out.println(Thread.currentThread().getName() + " get lock, " +
                        "status = " + Thread.currentThread().getState());
            }
            try {
                /*
                计时等待(timed waiting)
                此处为等待被stop
                这一状态将一直保持到超时期满或者接受到适当的通知
                带有超时参数的方法有:
                Thread.sleep和Object.wait、Thread.join、Lock.tryLock以及Condition.await的计时版
                 */
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(thread.getName() + " is a new thread, " +
                "status = " + thread.getState());
        /*
        可运行线程(Runnable)
        可能正在运行也可能没有 取决于操作系统给线程提供运行的时间
        1.所有桌面及服务器操作系统都采用抢占式调度
          给每一个可运行线程一个时间片来执行任务
          时间片用完 操作系统剥夺该线程的运行权 并给另一个线程运行机会
        2.手机这样的小型设备可能使用协作式调度
          一个线程只有在调用yield方法或者被阻塞或等待时 线程才失去控制权
        多处理器机器上 每一个处理器运行一个线程 可以有多个线程并行运行
        但线程数目多于处理器数目时 调度器依然采用时间片机制
         */
        thread.start();
        // 等待thread执行到wait
        Thread.sleep(10);
        System.out.println(thread.getName() + " is wait, " +
                "status = " + thread.getState());
        synchronized (lock) {
            // 唤醒thread 但不会交出锁
            lock.notify();
            Thread.sleep(10);
            System.out.println(thread.getName() + " cant get lock, " +
                    "status = " + thread.getState());
        }
        // 等待thread进入sleep
        Thread.sleep(10);
        System.out.println(thread.getName() + " is sleep, " +
                "status = " + thread.getState());
        /*
        被终止的线程(Terminated)
        线程因为以下原因被终止
        1.因为run方法正常退出而自然死亡
        2.因为一个没有捕获的异常终止了run方法而意外死亡
        特别的，可以调用stop方法杀死一个线程
        stop方法抛出 ThreadDeath 错误对象 由此杀死线程
        stop方法已过时 不要在自己的代码中调用这个方法
         */
        thread.stop();
        // 等待stop结束
        Thread.sleep(10);
        // println方法有两个操作 print 和 newLine
        // 由于强制停止了thread 可能thread中的println(注意不是此处的println)未执行完毕就被终止了
        // 如只执行了print未执行newLine、print和newLine都未执行等
        System.out.println(thread.getName() + " is called stop, " +
                "status = " + thread.getState());
    }
}
