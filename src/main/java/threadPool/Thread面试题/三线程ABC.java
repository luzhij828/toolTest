package threadPool.Thread面试题;

/**
 * Created by luzhijie on 2017/8/22.
 */
public class 三线程ABC {
    //其中这几个例子的实例用的是同一个对象中不同方法问题
    //添加flag标记，在三个上锁的方法中进行循序切换
    public static void main(String[] args) {
        MyObject2 my = new MyObject2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                   my.printA();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                   my.printB();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                   my.printC();
                }
            }
        }).start();
    }
}

class MyObject2{
    private int flag = 1;
    public synchronized void printA(){
        while (flag!=1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        flag = 2;
        this.notifyAll();
    }
    public synchronized void printB(){
        while (flag!=2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        flag = 3;
        this.notifyAll();
    }
    public synchronized void printC(){

        while (flag!=3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("C");
        flag = 1;
        this.notifyAll();
    }
}
