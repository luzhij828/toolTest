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
public class test22 {

    static Map<String,String> cookie = new HashMap<>();
    static Map<String,String> data1 = new HashMap<>();

    public static void main(String[] args) throws IOException {



        System.out.println(Jsoup.connect("http://www.baidu.com").timeout(99999).proxy("114.82.159.57", 8118).get().body());
    }



}
