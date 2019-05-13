package multithreading.synchronization;

/**
 * Study Notes
 * ------------
 * 1. volatile域
 *    多处理器计算机能够暂时在寄存器或本地内存缓冲区保存内存中的值
 *    编译器可以改变指令执行的顺序以使吞吐量最大化
 *    volatile变量不能提供原子性 只提供可见性
 *    也就是说 当某线程改变此值 其他线程再行读取到的是新值
 *    但已读取的值被其他线程改变 这个已读取的值依旧是旧值
 * 2. final变量
 *    声明为final的域 在被某线程赋值后 其他线程再行读取到的是新值
 *    如果是对象 则为构造函数完成构造之后的值
 */
public class Visibility {

    private volatile boolean done;

    boolean isDone() {
        return done;
    }

    void setDone(boolean done) {
        this.done = done;
    }
}
