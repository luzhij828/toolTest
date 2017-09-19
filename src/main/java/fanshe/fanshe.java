package fanshe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by luzhijie on 2017/9/11.
 */
public class fanshe {
    /**
     * java.util.Properties
     * 测试使用反射读取配置文件(成功)
     * @param args
     */
    public static void main(String[] args) {

        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream("res/param.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String aaa = properties.getProperty("aaa");
        System.out.println("url"+aaa);
    }
}
