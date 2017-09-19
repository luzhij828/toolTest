package jsoup.daili.httpbrowser;


import jsoup.daili.IPModel.IPMessage;
import org.jsoup.Connection;
import org.jsoup.Connection.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static java.lang.System.out;

/**
 * Created by paranoid on 17-4-10.
 */

public class URLFecter {
    static  List<IPMessage> ipMessages = new ArrayList<>();

    public static List<IPMessage> gethtml(String url) {

        Connection conn = Jsoup.connect(url).ignoreContentType(true).method(Method.GET).timeout(6000);
        conn.header("Accept","application/json, text/javascript, */*; q=0.01");
        conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
        Response rs = null;
        try {
            rs = conn.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getlist(rs.body());
    }


    public static List<IPMessage> getlist(String html){

            //将html解析成DOM结构
            Document document = Jsoup.parse(html);

            //提取所需要的数据
            Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

            for (int i = 1; i < trs.size(); i++) {
                IPMessage ipMessage = new IPMessage();
                String ipAddress = trs.get(i).select("td").get(1).text();
                String ipPort = trs.get(i).select("td").get(2).text();
                String serverAddress = trs.get(i).select("td").get(3).text();
                String ipType = trs.get(i).select("td").get(5).text();
                String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]").
                        attr("title");

                ipMessage.setIPAddress(ipAddress);
                ipMessage.setIPPort(ipPort);
                ipMessage.setServerAddress(serverAddress);
                ipMessage.setIPType(ipType);
                ipMessage.setIPSpeed(ipSpeed);

                ipMessages.add(ipMessage);
            }
        return ipMessages;
    }
}
