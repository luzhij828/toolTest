package jsoup.email;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by luzhijie on 2017/8/22.
 */
public class getBaidu {

    public static void main(String[] args) {
        //百度测试DOM获取a
//        getDom1();
        //测试选择器1
        getSelect2();


        //
//        getIMG();

    }
    public static void getIMG() {
        try {
            String url = "https://www.baidu.com";
            Document conn = Jsoup.connect(url).header("","").get();

            Elements list = conn.select("img");

            for (Element img:list){
                String imgurl = img.attr("src");
                String name = imgurl.substring(imgurl.lastIndexOf("/")+1);
                System.out.println("图片地址"+imgurl);
                download(imgurl,name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void download(String imgurl,String name){
        try {
            String path1 = "H:\\";
            File file = new File(path1+name);
            URL url = new URL("http:"+imgurl);
            // 获得连接
            URLConnection connection = url.openConnection();
            // 设置10秒的相应时间connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            InputStream in = connection.getInputStream();
            //获得输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[1024];
            int size;
            while (-1!=(size=in.read(buf))){
                out.write(buf,0,size);
            }
            out.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取dom
    public static void getDom1(){
        try {
            Document doc =  Jsoup.connect("http://news.baidu.com/").get();

            Element content = doc.getElementById("header-link-wrapper");
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                System.out.println(linkText+"   "+linkHref);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //用selector
    public static void getSelect1(){
        try {
            Document doc =  Jsoup.connect("http://news.baidu.com/").get();

            Elements content = doc.select("#header-link-wrapper");
            Elements links = content.select("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                System.out.println(linkText+"   "+linkHref);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //用selector
    public static void getSelect2(){
        try {
            Document doc =  Jsoup.connect("http://news.baidu.com/").get();
            Elements links = doc.select("#header-link-wrapper a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                System.out.println(linkText+"   "+linkHref);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
