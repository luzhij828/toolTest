package DBsql.index;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by luzhijie on 2017/8/15.
 */
public class MysqlDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?"
                + "user=root&password=password&useUnicode=true&characterEncoding=UTF8";
        String sql = "select * from test1";

        MysqlDB mysqlDB = new MysqlDB();
        System.out.println(mysqlDB.getName(url,sql));
    }
    public  String getName(String dburl,String sqll){
        Connection conn = null;
        String url = dburl;
        String sql=sqll;
        String names="";
//        sql = "select * from test1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                names=names+rs.getString("name")+",";
//                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return names;
    }

}
