package multithreading.synchronization;

/**
 * Study Notes
 * ------------
 * 多个线程对同一资源的存取
 * 根据各线程访问数据的次序，可能会产生讹误的对象
 * 这一情况称为竞争条件(race condition)
 */
public class Compete {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(40);
        Account account1 = new Account(40);
        AccountSync accountSync = new AccountSync(40);

        // 模拟100个人同时从账户取钱和向账户存钱
        for (int i = 0; i < 100; i++) {
            // 未加锁
            new Thread(account::consume).start();
            new Thread(account::produce).start();
            // 加锁
            new Thread(account1::consumeSecurity).start();
            new Thread(account1::produceSecurity).start();
            // synchronized同步
            new Thread(accountSync::consume).start();
            new Thread(accountSync::produce).start();
        }

        // 等待所有人取钱结束
        Thread.sleep(3000);
        System.out.println("account money = " + account.getMoney());
        System.out.println("account1 money = " + account1.getMoney());
        System.out.println("accountSync money = " + accountSync.getMoney());
    }
}
