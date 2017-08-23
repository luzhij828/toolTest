package tool;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;

public class MainTest {

    DBImpl db;


    public void before(){

        String url = "";
        String user = "";
        String passw = "";
        String type = "";

        try {
            //使用反射将配置文件中的参数读取出来，应该是这样写吧
            Properties properties= new Properties();
            properties.load(new FileInputStream("build/resources/main/param1.properties"));

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            passw = properties.getProperty("password");
            type = properties.getProperty("type");

            Class cls = Class.forName(type);
            Constructor constructor = cls.getConstructor(String.class, String.class, String.class);
            db = (DBImpl) constructor.newInstance(url,user,passw);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void TestSqlite() throws Exception{
        ResultSet result = db.queryAll("select name from simple_user");
        while(result.next()){
            Log.log(result.getString("name"));
        }
        Log.log("###########finish#############");
    }


    public void after(){
        db.close();
    }

}
