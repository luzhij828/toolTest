package DBsql.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBImpl {

    public Connection con;
//使用父类继承子类的方法将，一点点的公共方法进行封装
    public ResultSet queryAll(String sql){
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
//关闭数据库连接
    public boolean close(){
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
