package multithreading.attribute;

/**
 * Study Notes
 * ------------
 * 守护线程(Daemon)
 * 1.为其他线程提供服务
 * 2.当只剩守护线程时 虚拟机就退出了
 * 3.守护线程不应该去访问固有资源 如文件、数据库
 *   因为它会在任何时间甚至一个操作的中间发生中断
 */
public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread didi = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Didi");
            }
        });
        didi.setDaemon(true);
        didi.start();
        System.out.println("7355608");
        Thread.sleep(5000);
        System.out.println("Boom");
    }
}
