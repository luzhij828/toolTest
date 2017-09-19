package jsoup.daili.database;


import jsoup.daili.IPModel.IPMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 操作数据库用的
 * Created by paranoid on 17-4-12.
 */
public class DataBaseDemo {
    private static String driver = "com.mysql.jdbc.Driver";    //数据库驱动
    private static String dbURL = "jdbc:mysql://127.0.0.1:3306/IPProxy" +
            "?characterEncoding=utf8&useSSL=true";    //操作的数据库地址，端口及库名
    private static String user = "root";                       //数据库用户名
    private static String password = "password";            //数据库密码

    //数据库添加功能
    public static void add(List<IPMessage> list) throws ClassNotFoundException {
        Class.forName(driver);                         //加载数据库驱动

        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);


            PreparedStatement statement = conn.prepareStatement("INSERT INTO " +
                    "ProxyPool (IPAddress, IPPort, serverAddress, IPType, IPSpeed)" +
                    " VALUES (?, ?, ?, ?, ?)");
            for (IPMessage ipMessage : list) {

                statement.setString(1, ipMessage.getIPAddress());
                statement.setString(2, ipMessage.getIPPort());
                statement.setString(3, ipMessage.getServerAddress());
                statement.setString(4, ipMessage.getIPType());
                statement.setString(5, ipMessage.getIPSpeed());

                statement.executeUpdate();
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除数据库指定IP
    public static void deleteIP(int IPid) {
        String sql = "DELETE FROM ProxyPool WHERE id = " + IPid;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //数据库表清除功能(id也一并清除)
    public static void delete() {
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            statement.executeUpdate("TRUNCATE TABLE ProxyPool");

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //数据库查找功能
    public static List<IPMessage> query() throws ClassNotFoundException {
        Class.forName(driver);                         //加载数据库驱动
        List<IPMessage> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ProxyPool");

            while (resultSet.next()) {
                IPMessage ipMessage = new IPMessage();

                ipMessage.setId(resultSet.getString(1));
                ipMessage.setIPAddress(resultSet.getString(2));
                ipMessage.setIPPort(resultSet.getString(3));
                ipMessage.setServerAddress(resultSet.getString(4));
                ipMessage.setIPType(resultSet.getString(5));
                ipMessage.setIPSpeed(resultSet.getString(6));

                list.add(ipMessage);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
