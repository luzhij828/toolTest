package tool;

import java.sql.*;

public class DBMysql extends DBImpl {

    //
    public DBMysql(String url, String user, String passw){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//            DriverManager.getDrivers();
             con = DriverManager.getConnection(url, user, passw);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
