package multithreading.synchronization;

/**
 * Study Notes
 * ------------
 * 1. Java中每个对象都有一个内部锁
 *    如果一个方法用 synchronized 声明 那么对象的锁保护整个方法
 *    要调用该方法 线程必须获得内部的对象锁
 * 2. 用 synchronized 声明的静态方法
 *    该方法获得相关的类对象的内部锁 如 AccountSync.class 对象的锁
 *    当这个静态方法被一个线程调用时
 *    其他线程都无法调用同一个类的这个或任何其他的同步静态方法
 * 3. void notifyAll()
 *    解除那些在该对象上调用 wait 方法的线程的阻塞状态
 *    该方法只能在同步方法或同步块内部调用
 * 4. void notify()
 *    随机选择一个在该对象上调用 wait 方法的线程
 * 5. void wait()
 *    导致线程进入等待状态知道它被通知
 * 6. void wait(long millis)
 *    导致线程进入等待状态知道它被通知或经过指定的时间
 * 7. 调用以上方法时 如果当前线程不是对象锁的持有者
 *    该方法抛出一个 IllegalMonitorStateException 异常
 */
class AccountSync {

    private int money;

    AccountSync(int money) {
        this.money = money;
    }

    synchronized void consume() {
        try {
            Thread.sleep(Double.valueOf(Math.random() * 10).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money--;
    }

    synchronized void produce() {
        try {
            Thread.sleep(Double.valueOf(Math.random() * 10).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money++;
    }

    int getMoney() {
        return this.money;
    }
}
