package jsoup.email;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luzhijie on 2017/8/22.
 */
public class Email21cn {
    static Map<String,String> ck = new HashMap<>();
    static Map<String,String> user = new HashMap<>();
    static Map<String,String> user1 = new HashMap<>();

    public static void main(String[] args) {
        user.put("accountType","02");
        user.put("appId","8013411507");
        user.put("password","123qwe");
        user.put("userName","luzhij828@21cn.com");
        user.put("apptype","web");
        user.put("returnUrl","http://mail.21cn.com/w2/logon/unifyPlatformLogonReturn.do?LSID=aZ4BQ7UcUAz9rxeUm4");

        getCK();
    }
    public static void getCK() {
            ck.put("pageOp","39694808e0e5ce6e528f30db4035c7e7");
        try {
            //先请求第一个请求
//            String url1 = "http://open.e.189.cn/api/common/needcaptcha.do?appId=8013411507";
//            Connection conn1 = Jsoup.connect(url1).ignoreContentType(true).method(Method.POST).cookies(ck).data(user).timeout(6000);
//            System.out.println(conn1.execute().body());



//            String url = "https://open.e.189.cn/api/common/loginSubmit.do";
            String url = "https://www.baidu.com";
            Document conn = Jsoup.connect(url).get();

//            Response rs= conn.execute();
//            ck=rs.cookies();
            System.out.println(conn.body());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
