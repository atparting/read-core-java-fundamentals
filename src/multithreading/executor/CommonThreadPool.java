package multithreading.executor;

import java.util.concurrent.*;

public class CommonThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<Boolean> submit = pool.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }, true);
        pool.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
        }, true);
        pool.shutdown();
        System.out.println(submit.get());
        System.out.println("largestPoolSize = " + ((ThreadPoolExecutor)pool).getLargestPoolSize());
    }
}
