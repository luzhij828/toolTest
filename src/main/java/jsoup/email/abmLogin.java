package jsoup.email;

import org.jsoup.Connection;
import org.jsoup.Connection.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luzhijie on 2017/8/24.
 */
public class abmLogin {

    static Map<String,String> cookie = new HashMap<>();
    static Map<String,String> data1 = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //登录内网奥霸码
        //第一次请求---加载登录页面获得一部分cookie，和参数
        String url1 = "http://172.16.248.4/user/login";

        Connection conn1 = Jsoup.connect(url1).method(Method.GET).ignoreContentType(true);

        conn1.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn1.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");

        //发送请求获得响应信息
        Response rs1 = conn1.execute();
        //获取响应cookies
        cookie.putAll(rs1.cookies());

        //设置参数信息
        data1.put("_csrf",cookie.get("_csrf"));
        data1.put("user_name","364460369@qq.com");
        data1.put("password","123456");



        //第二次请求---登录请求
        String url2="http://172.16.248.4/user/login";
        Connection conn2 = Jsoup.connect(url2).cookies(cookie).data(data1).method(Method.POST).ignoreContentType(true);
        conn2.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
        Response rs2 = conn2.execute();
        //响应头，
//        Map<String,String> h1=rs2.headers();
//        for (String k:h1.keySet()){
//            System.out.println(h1.get(k));
//        }
        cookie.putAll(rs2.cookies());



        //第三次请求---进入主页
        String url3 = "http://172.16.248.4/";
        Document conn3 = Jsoup.connect(url3).cookies(cookie).get();


        Elements list1 = conn3.select("div.ui.fifteen.wide.column>div.push.news");
        for (int i=0;i<list1.size();i++){
            String b1 = list1.get(i).text();

            System.out.println("——————————————————————");
            System.out.println(b1);
        }
//        System.out.println(conn3.body());
    }


}
