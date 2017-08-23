package threadPool.Thread面试题;

import jdk.nashorn.internal.objects.annotations.Where;

/**
 * Created by luzhijie on 2017/8/22.
 */
public class 双线程交替打印 {
    public static void main(String[] args) {
        MyObjectl my = new MyObjectl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<26;i++){
                    my.printN();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<26;i++){
                    my.printA();
                }
            }
        }).start();
    }

}

class MyObjectl{
    private static boolean flag = true;
    public int count = 1;

    public  synchronized void printN(){
        while (flag==false){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(2*count-1);
        System.out.print(2*count);
        flag=false;
        this.notify();
    }
    public synchronized void printA(){
        while (flag ==true){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print((char)(count+'A'-1));
        count++;
        flag = true;
        this.notify();
    }
}