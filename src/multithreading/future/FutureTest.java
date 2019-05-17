package multithreading.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) {
        MatchCount matchCount = new MatchCount("dog", "i like dog but dog dont like me");
        FutureTask<Integer> task = new FutureTask<>(matchCount);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
