package multithreading.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ListUsage {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> chm1 = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> chm.compute("dog", (k, v) -> v == null ? 1 : v + 1)).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                Integer value = chm1.get("dog");
                chm1.put("dog", value == null ? 1 : value + 1);
            }).start();
        }
        System.out.println("chm dog = " + chm.get("dog"));
        System.out.println("chm1 dog = " + chm1.get("dog"));
    }
}
