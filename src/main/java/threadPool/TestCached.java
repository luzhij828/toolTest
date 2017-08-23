package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luzhijie on 2017/8/18.
 */
public class TestCached {
    public static void main(String[] args) {
        //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        long start = System.currentTimeMillis();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i=0;i<1000;i++){
            final int index = i;
//            try {
//                Thread.sleep(index*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        long end = System.currentTimeMillis();
        cachedThreadPool.shutdown();
        System.out.println("运行时间："+(end-start)+"毫秒");

    }
}
