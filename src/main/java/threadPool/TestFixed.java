package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luzhijie on 2017/8/18.
 */
public class TestFixed {
    //创建定长大小的线程池
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new MyRunnable());
        pool.execute(new MyRunnable());

        pool.shutdown();

    }

}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int x=0;x<10;x++){
            System.out.println(Thread.currentThread().getName()+": "+x);
        }
    }
}