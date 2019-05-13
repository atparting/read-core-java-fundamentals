package multithreading.attribute;

/**
 * Study Notes
 * ------------
 * 未捕获异常处理器
 * 1. 线程run方法不能抛出任何受查异常 非受查异常会导致线程终止
 * 2. static void setDefaultUncaughtExceptionHandler
 *    为所有线程安装一个默认的处理器
 * 3. 线程组 ThreadGroup 是一个可以统一管理的线程集合
 *    默认创建的所有线程属于同一个线程组
 *    如果不为线程安装处理器 那么处理器就是所属的线程组
 *    线程组 uncaughtException 方法处理策略：
 *    1) 如果有父线程组，调用父线程组的uncaughtException
 *    2) 否则，如果Thread.getDefaultUncaughtExceptionHandler返回一个非空的处理器，则调用该处理器
 *    3) 否则，如果Throwable是ThreadDeath的一个实例，什么都不做
 *    4) 否则，线程的名字以及Throwable的栈轨迹被输出到System.err上
 */
public class Handler {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int a = Integer.parseInt("a");
            System.out.println(a);
        });
        Thread thread1 = new Thread(() -> {
            int b = Integer.parseInt("b");
            System.out.println(b);
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("t = " + t.getName() + ", e = " + e.toString());
        });
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("t = " + t.getName() + ", e = " + e.toString());
        });
        thread.start();
        thread1.start();
    }
}
