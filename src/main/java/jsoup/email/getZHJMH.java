package jsoup.email;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.*;
import org.jsoup.Jsoup;

import java.io.*;


/**这是我将一整部漫画下载下来的栗子---完成
 * Created by luzhijie on 2017/9/4.
 */
public class getZHJMH {

    static String zhangjie = "";
    static String path = "F:\\XZTEST\\";

    public static void main(String[] args) {
        //这里便是一个兴趣实例，将一整部漫画下载下来，
        //漫画网站一般为了流量只支持在线阅读。
        //还有一个意外发现，我们可以“绕过”这个网站的VIP--（是他们网站漏洞）


        //目前章节只是到了194
        for (int i=193;i<195;i++) {
            String url = "http://www.migudm.cn/opus/webQueryWatchOpusInfo.html?hwOpusId=000000014934&index="+i+"&opusType=2";
            Connection conn = Jsoup.connect(url).ignoreContentType(true).method(Method.GET).timeout(6000);
            conn.header("Accept","application/json, text/javascript, */*; q=0.01");
            conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");

            try {
                Response rs = conn.execute();

                //将响应正文进行处理
                getlist(rs.body());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getlist(String jsonbody){

        JSONObject j1 = JSONObject.fromObject(jsonbody);

        JSONArray list1 = j1.getJSONObject("data").getJSONArray("jpgList");
        zhangjie = path+j1.getJSONObject("data").getJSONObject("info").getString("itemName");

//        System.out.println("zhangjie:"+zhangjie);

        if(zhangjie!=null||!(zhangjie.equals(""))){
            File file1 = new File(zhangjie);
            if(file1.mkdirs()){
                System.out.println("章节目录创建成功！ "+zhangjie);
            }
        }
        for (int  a=0 ;a<list1.size();a++){
            JSONObject obj = list1.getJSONObject(a);
            //文件名
            String imgname = obj.getString("fileName");
            //文件地址
            String imgurl = obj.getString("url");
            System.out.println(imgname+"    "+imgurl);

            //下载文件
            getImg(imgurl,imgname);

        }


    }

    //将图片下载到本地
    public static void getImg(String imgurl ,String imgname){
        try {
            File file = new File(zhangjie+"\\"+imgname);
//            URL url = new URL("http:"+imgurl);
            // 获得连接
//            URLConnection connection = url.openConnection();

            Connection conn = Jsoup.connect(imgurl).ignoreContentType(true).method(Method.GET).timeout(60000);

            Response rs = conn.execute();

            // 设置10秒的相应时间connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            byte[] in = rs.bodyAsBytes();
            //获得输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//            byte[] buf = new byte[1024];
//            int size;
//            while (-1!=(size=in.read(buf))){
//                out.write(buf,0,size);
//            }
            out.write(in);
            out.close();
//            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

