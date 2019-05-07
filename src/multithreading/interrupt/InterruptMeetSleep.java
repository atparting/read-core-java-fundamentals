package multithreading.interrupt;

/**
 * Study Notes
 * ------------
 * 1. 如果线程被阻塞 则无法检测中断状态
 *    这是产生 InterruptedException 异常的地方
 * 2. 当在一个被阻塞的线程(调用 sleep 或 wait)上调用 interrupted 时
 *    阻塞调用会被 InterruptedException 异常中断
 * 3. 如果每次迭代之后都调用 sleep 方法(或者其他可中断的方法)
 *    isInterrupted 检测既没有必要也没有用处
 * 4. 如果在中断状态被置位(true)时调用 sleep 方法
 *    它不会休眠 会清除这一状态并抛出 InterruptedException 异常
 */
public class InterruptMeetSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 应该在此外层使用 try catch finally
            while (!Thread.currentThread().isInterrupted()) {
                // 等待 interrupt 被置位
                boolean firstWait = true;
                while (!Thread.currentThread().isInterrupted()) {
                    if (firstWait) {
                        System.out.println("wait...");
                        firstWait = false;
                    }
                }
                System.out.println("before sleep interrupt = " + true);
                // 不建议将InterruptedException异常抑制在这种很低的层次上
                // 抛出交给调用者处理 或在catch中设置中断状态 如本例
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("after sleep interrupt = " + Thread.currentThread().isInterrupted());
                    // 设置中断状态
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(10);
        System.out.println("call interrupt");
        thread.interrupt();
    }
}
