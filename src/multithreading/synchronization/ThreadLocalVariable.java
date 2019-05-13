package multithreading.synchronization;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Study Notes
 * ------------
 * ThreadLocal
 * 1. 例如 SimpleDateFormat 不是线程安全的
 *    如果多个线程都执行 format() 操作 结果会很混乱
 * 2. 使用ThreadLocal辅助类为各个线程提供各自的实例
 *    避免共享变量
 * 3. T get()
 *    得到这个线程的当前值 如果是首次调用 会调用 initialize 来得到这个值
 *    protected T initialValue()
 *    提供一个初始值 默认返回null
 *    void set(T t)
 *    为这个线程设置一个新值
 *    void remove()
 *    删除对应这个线程的值
 *    static <S> ThreadLocal<S> withInitial(Supplier< ? extends S> supplier)
 *    创建一个线程局部变量 其初始值通过调用给定的supplier生成
 *
 * ThreadLocalRandom
 * 1. java.util.Random是线程安全的
 *    但是如果多个线程需要等待一个共享的随机数生成器 这会很低效
 * 2. ThreadLocalRandom.current()会返回特定于当前线程的Random实例
 */
public class ThreadLocalVariable {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final ThreadLocal<SimpleDateFormat> localFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        Date date = new Date(1557481715000L);
        Date date1 = new Date(1512354681000L);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String format = ThreadLocalVariable.format.format(date);
                String format1 = localFormat.get().format(date);
                System.out.println(format + "\t" + format1);
            }).start();
            new Thread(() -> {
                ThreadLocalVariable.format.format(date1);
                localFormat.get().format(date1);
            }).start();
        }
    }
}
