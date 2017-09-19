package DBsql.c3p0Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by luzhijie on 2017/8/17.
 */
public class ConnectionPool {
    private static ComboPooledDataSource ds;
    private static ConnectionPool pool;
    //构造函数
    private ConnectionPool() {

        ds = new ComboPooledDataSource();
    }
    public static  final  ConnectionPool getInstance(){
        if (pool==null){
            pool=new ConnectionPool();
        }
        return pool;
    }
    //获取连接池中的连接
    public synchronized  final Connection getConnection(){
        try {
            Connection conn = ds.getConnection();
            return  conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
