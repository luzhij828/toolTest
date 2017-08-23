package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luzhijie on 2017/8/18.
 */
public class TestFixed2 {
    //这个也是创建定长大小的线程池
    public static void main(String[] args) {
        ExecutorService fixedThreadPoll = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            final int index =i;

            fixedThreadPoll.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}


