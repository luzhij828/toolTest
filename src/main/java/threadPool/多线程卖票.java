package threadPool;

/**
 * Created by luzhijie on 2017/8/17.
 */
public class 多线程卖票 {
    //测试多线程同步代码块,几个窗口卖票的例子
    public static void main(String[] args) {
        Tickcet tickcet = new Tickcet();
        Thread t1 = new Thread(tickcet,"窗口1");
        Thread t2 = new Thread(tickcet,"窗口2");
        Thread t3 = new Thread(tickcet,"窗口3");
        Thread t4 = new Thread(tickcet,"窗口4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}


class Tickcet implements Runnable{
    public int ticket = 400;
    @Override
    public void run() {
        while (ticket>0){
            saleTicket();
        }
    }
    public synchronized void saleTicket(){
        if (ticket>0){
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getName()+"---卖出"+(ticket--)+"号票");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}