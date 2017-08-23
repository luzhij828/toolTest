package threadPool;

/**
 * Created by luzhijie on 2017/8/17.
 */
public class ThreadTest2 {
    //线程之间的交互对话--还不是太明白
    public static void main(String[] args) {
        User p=new User();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int x=0;
                int m = 100;
                while (m>0) {
                    synchronized (p) {
                        if (x == 0) {
                            p.set("张三", "12");
                        } else {
                            p.set("李四", "18");
                        }
                        x = (x + 1) % 2;
                        m--;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int m=100;
                while (m>0){
                    synchronized (p){
                        System.out.println(p.get());
                        m--;
                    }
                }
            }
        }).start();
    }
}
class  User{
    public String name;
    public String gender;
    public void set(String name,String gender){
        this.name=name;
        this.gender=gender;
    }
    public String get(){
        return this.name +" : "+this.gender;
    }
}
