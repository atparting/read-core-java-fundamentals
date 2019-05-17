package multithreading.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CommonUsage {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(100);
                queue.put("dog");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        String take = queue.take();
        System.out.println("take = " + take);
    }
}
