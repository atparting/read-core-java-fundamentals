package multithreading.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Study Notes
 * ------------
 * ReentrantLock
 * 1. ReentrantLock(boolean fair)
 *    构建一个带有公平策略的锁 默认不强制为公平的
 *    公平锁偏爱等待时间最长的线程 但会大大降低性能
 * 2. void lock()
 *    获取这个锁 如果锁同时被另一个线程拥有则发生阻塞
 *    void unlock()
 *    释放这个锁
 * 3. Condition newCondition()
 *    返回一个与该锁相关的条件对象
 * Condition
 * 1. await()
 *    将该线程放到条件的等待集中
 * 2. signalAll()
 *    解除该条件的等待集中的所有线程的阻塞状态
 * 3. signal()
 *    从随机解除该条件的等待集中一个线程的阻塞状态
 */
class Account {

    private Lock lock = new ReentrantLock();
    private Condition condition;
    private int money;

    Account(int money) {
        this.money = money;
        this.condition = lock.newCondition();
    }

    void consume() {
        try {
            // 模拟网络延迟
            Thread.sleep(Double.valueOf(Math.random() * 10).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money--;
    }

    void produce() {
        try {
            Thread.sleep(Double.valueOf(Math.random() * 20).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money++;
    }

    void consumeSecurity() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            while (this.money <= 0) {
                System.out.println("money = " + this.money + ", await...");
                condition.await();
            }
            this.money--;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void produceSecurity() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            this.money++;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    int getMoney() {
        return this.money;
    }
}
