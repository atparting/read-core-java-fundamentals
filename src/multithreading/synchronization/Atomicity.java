package multithreading.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Study Notes
 * ------------
 * java.util.concurrent.atomic包中
 * 有很多类使用类很高效的机器级指令(而不是使用锁)
 * 保证操作的原子性
 */
public class Atomicity {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            new Thread(integer::incrementAndGet).start();
        }
        Thread.sleep(100);
        System.out.println(integer.get());

        new Thread(() -> {
            integer.updateAndGet(x -> Math.max(x, 200));
        }).start();
        Thread.sleep(100);
        System.out.println(integer.get());
    }
}
