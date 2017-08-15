package demo.index;

import java.sql.*;

/**
 * Created by luzhijie on 2017/8/15.
 */
public class SqliteDB {
    public static void main(String[] args) {
        String dbsql = "D:\\IdeaProjects\\test0815\\src\\main\\resources\\sqliteDB.db3";
        String sql="select * from simple_user;";
        SqliteDB sqliteDB = new SqliteDB();
        System.out.println(sqliteDB.getNames(dbsql,sql));
    }

public String getNames(String dbsql, String sqll){
    String dburl="jdbc:sqlite://"+dbsql;
    Connection conn=null;
    Statement stat;
    ResultSet rs;
    String names="";
    try {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(dburl);
        stat = conn.createStatement();
        rs = stat.executeQuery(sqll);
        while (rs.next()) {
            names = names+rs.getString("name") + ", ";
//            System.out.print("name = " + rs.getString("name") + ", ");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
return names;
}
}
