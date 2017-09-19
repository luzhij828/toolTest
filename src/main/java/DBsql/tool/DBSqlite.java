package DBsql.tool;

import java.sql.*;

public class DBSqlite extends DBImpl {

    public DBSqlite(String url, String user, String password){
        try{
            Class.forName("org.sqlite.JDBC");
//            DriverManager.getDrivers();
            con = DriverManager.getConnection(url);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
