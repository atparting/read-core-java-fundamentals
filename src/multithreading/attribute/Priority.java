package multithreading.attribute;

/**
 * Study Notes
 * ------------
 * 线程优先级(Priority)
 * 1.默认一个线程继承它的父线程的优先级
 * 2.线程优先级是高度依赖于操作系统的
 *   当虚拟机依赖于宿主机平台的线程实现机制时
 *   Java线程的优先级被映射到宿主机平台的优先级上
 *   Windows有7个优先级 Oracle为Linux提供的虚拟机中线程的优先级将被忽略
 * 3.不要将程序构建为功能的正确性依赖于优先级
 */
public class Priority {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MAX_PRIORITY");
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("NORM_PRIORITY");
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MIN_PRIORITY");
            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread1.setPriority(Thread.NORM_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread.start();
        thread1.start();
        thread2.start();

        Thread.sleep(10);
        synchronized (lock) {
            lock.notifyAll();
            Thread.sleep(10);
        }
    }
}
