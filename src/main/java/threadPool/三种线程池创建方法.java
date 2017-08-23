package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by luzhijie on 2017/8/18.
 */
public class 三种线程池创建方法 {
    public static void main(String[] args) {
        三种线程池创建方法 threadPool = new 三种线程池创建方法();
//        ExecutorService exec=threadPool.getCachedTP();
//        ThreadPoolExecutor exec2 = (ThreadPoolExecutor)threadPool.getCachedTP();
        ThreadPoolExecutor exec2 = (ThreadPoolExecutor)threadPool.getFixedTP(1);
//        ThreadPoolExecutor exec2 = (ThreadPoolExecutor)threadPool.getSingleTP();//这个好像不行
//        //重点到了!!
//        //将exec转换为ThreadPoolExecutor,ThreadPoolExecutor有方法 getActiveCount()可以得到当前活动线程数
//        int threadCount = ((ThreadPoolExecutor)exec).getActiveCount();
//        System.out.println(threadCount);
        for (int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            exec2.execute(myTask);

            System.out.println("总数："+exec2.getPoolSize()+"    等待数："+exec2.getQueue().size()
                    + "    完成数："+exec2.getActiveCount());
        }
        exec2.shutdown();

    }

    /**
     * 创建一个不定长线程池
     * @return
     */
    public ExecutorService getCachedTP(){

        return Executors.newCachedThreadPool();
    }

    /**
     * 创建一个定长线程池
     * @param a
     * @return
     */
    public ExecutorService getFixedTP(int a){
        return Executors.newFixedThreadPool(a);
    }

    /**
     *创建一个长度为一的线程池
     * @return
     */
    public ExecutorService getSingleTP(){
        return Executors.newSingleThreadExecutor();
    }


}
class MyTask implements Runnable{
    private  int taskNum;

    public MyTask(int taskNum){
        this.taskNum=taskNum;
    }
    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task"+taskNum+"执行完毕");
    }
}
