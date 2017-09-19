package DBsql.c3p0Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luzhijie on 2017/8/17.
 */
public class C3Test {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("select * from test1");
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
            System.out.println("他说是使用的数据库连接池");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
