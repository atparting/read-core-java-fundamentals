package multithreading.status;

/**
 * Study Notes
 * ------------
 * 1.wait会使当前线程让出cpu给其他线程
 * 2.wait会持续更新时间
 *   不会等待当前线程获得执行权时再更新
 *   因为它的监控(monitors)状态依然保持着
 *   当指定的时间到了又会自动恢复运行状态
 * 3.wait会使当前线程释放对象锁
 * 4.wait是阻塞的 可能抛出 InterruptedException 异常
 * 5.wait必须在同步块或同步方法中调用
 */
public class Wait {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    try {
                        lock.wait(3000);
                        System.out.println(Thread.currentThread().getName() + " wait over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        synchronized (lock) {
            lock.wait(3000);
            System.out.println(Thread.currentThread().getName() + " wait over");
        }
    }
}
