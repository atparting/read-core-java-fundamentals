package multithreading.interrupt;

/**
 * Study Notes
 * ------------
 * 1.sleep会使当前线程让出cpu给其他线程
 * 2.sleep会持续更新时间
 *   不会等待当前线程获得执行权时再更新
 *   因为它的监控(monitors)状态依然保持着
 *   当指定的时间到了又会自动恢复运行状态
 * 3.sleep过程中线程不会释放对象锁
 * 4.sleep是阻塞的 可能抛出 InterruptedException 异常
 */
public class Sleep {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " wake up");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " wake up");
    }
}
