package multithreading.future;

import java.util.concurrent.Callable;

public class MatchCount implements Callable<Integer> {

    private String keyword;
    private String sentence;

    MatchCount(String keyword, String sentence) {
        this.keyword = keyword;
        this.sentence = sentence;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        int count = 0;
        for (String word : sentence.split(" ")) {
            if (keyword.equals(word))
                count++;
        }
        return count;
    }
}
