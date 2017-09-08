package jsoup.email;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.JavaLangAccess;

import java.io.IOException;

public class test22{
    public static void main(String[] args) {

//        getCK();
        getJson();
    }
    public static void getCK() {
        try {
            //这就是简单的请求方式
            String url = "http://news.163.com/special/0001386F/rank_sports.html";
            Connection conn = Jsoup.connect(url).ignoreContentType(true).method(Method.GET).timeout(6000);

//            conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            conn.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//            conn.header("Content-Type","application/x-www-form-urlencoded");
//            conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
//            conn.header("Connection","keep-alive");
//            conn.header("Accept-Encoding","gzip, deflate, br");

            //响应回来的数据，带有响应头的
            Response rs= conn.execute();

//            System.out.println(rs.body());
            //解析响应回来的网页
            getList(rs.body());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getList(String body){
        //这里就是将响应正文转换成DOM树
        //之后用到的节奏和jquery差不多
        Document d1 =Jsoup.parse(body);

        Elements list1 = d1.select("div.area-half.left>div.tabBox>div.tabContents").get(0).select("table>tbody>tr>td>a");
//    	Elements list2 = list1.get(1).select("");
        int i=0;
        for(Element link :list1){
            i++;
            String href = link.attr("href");
            String title = link.text().trim();
            System.out.println(title+"   "+href);
        }
        System.out.println(i);
    }



    //解析json响应数据
    public static void getJson(){
        String json1 = "{\"user\":[{\"name\":\"张三\",\"age\":12},{\"name\":\"张四\",\"age\":13}]}";

        JSONObject j1 = JSONObject.fromObject(json1);
        JSONArray list1 = j1.getJSONArray("user");
        for (int  a=0 ;a<list1.size();a++){
            JSONObject obj = list1.getJSONObject(a);
            String name = obj.getString("name");
            String age = obj.getString("age");
            System.out.println(name+"   "+age);
        }


    }


}