package jsoup.daili.ipfilter;

import jsoup.daili.IPModel.IPMessage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;


import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by paranoid on 17-4-21.
 * 测试此Ip是否有效
 */

public class IPUtils {
    /**
     * 检测ip是否可用，就是使用一下
     * http://d.onlinedown.net/php/ajax_ip.php
     * 响应回ip信息
     * @param ipMessages
     * @return
     */
    public static List<IPMessage> IPIsable(List<IPMessage> ipMessages) {

        for(int i = 0; i < ipMessages.size(); i++) {
            String  ip = ipMessages.get(i).getIPAddress();
            String  port = ipMessages.get(i).getIPPort();
            try {
                Connection conn = Jsoup.connect("http://d.onlinedown.net/php/ajax_ip.php").ignoreContentType(true).proxy(ip, Integer.parseInt(port)).method(Connection.Method.GET).timeout(6000);
                conn.header("Accept","application/json, text/javascript, */*; q=0.01");
                conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
                Connection.Response rs = conn.execute();
                System.out.println(rs.body());

            } catch (IOException e) {
                out.println("不可用代理已删除" + ipMessages.get(i).getIPAddress() + ": " + ipMessages.get(i).getIPPort());
                ipMessages.remove(ipMessages.get(i));
                i--;
            }
        }

        return ipMessages;
    }


//    public static List<IPMessage> IPIsable(List<IPMessage> ipMessages) {
//        String ip;
//        String port;
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//
//        for(int i = 0; i < ipMessages.size(); i++) {
//            ip = ipMessages.get(i).getIPAddress();
//            port = ipMessages.get(i).getIPPort();
//
//            HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
//            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
//                    setSocketTimeout(3000).build();
//            HttpGet httpGet = new HttpGet("https://www.baidu.com");
//            httpGet.setConfig(config);
//
//            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
//                    "q=0.9,image/webp,*/*;q=0.8");
//            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
//            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
//                    "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//
//            try {
//                response = httpClient.execute(httpGet);
//            } catch (IOException e) {
//                out.println("不可用代理已删除" + ipMessages.get(i).getIPAddress() + ": " + ipMessages.get(i).getIPPort());
//                ipMessages.remove(ipMessages.get(i));
//                i--;
//            }
//        }
//
//        try {
//            httpClient.close();
//            response.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return ipMessages;
//    }
}
