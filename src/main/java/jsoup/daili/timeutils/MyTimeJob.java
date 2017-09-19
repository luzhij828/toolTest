package jsoup.daili.timeutils;


import jsoup.daili.IPModel.IPMessage;
import jsoup.daili.database.DataBaseDemo;

import jsoup.daili.httpbrowser.URLFecter;

import jsoup.daili.ipfilter.IPUtils;


import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by paranoid on 17-4-13.
 */

public class MyTimeJob  {
    /**
     * 网络爬虫ip代理的主要实现是，ip和端口必须有效才行
     * 所以需要去那些ip代理网站上进行大海捞针------我的初步理解
     */

    public static void main(String[] args) {
        MyTimeJob m = new MyTimeJob();
        m.execute();
    }
    //获取第一页ip列表
    public void execute(){
        //用来存放ip列表url的
        List<String> Urls = new ArrayList<>();
        //临时存放爬取回来的ip列表
        List<IPMessage> list = new ArrayList<>();
        //存放有效ip
        List<IPMessage> ipMessages = new ArrayList<>();

        String url = "http://www.xicidaili.com/nn/1";
        String IPAddress;
        String IPPort;


        //首先使用本机ip进行爬取,,,,现在使用的是我自己写的，
        //现在全部使用的是jsoup工具，原来的不会用，感觉好麻烦

        list = URLFecter.gethtml(url);

        //ip代理有效性验证，返回有些list、
        // 相当于使用一次代理请求
        // 访问的是华军软件
        ipMessages = IPUtils.IPIsable(list);

        for(IPMessage ipMessage : ipMessages){
            out.println(ipMessage.getIPAddress());
            out.println(ipMessage.getIPPort());
            out.println(ipMessage.getServerAddress());
            out.println(ipMessage.getIPType());
            out.println(ipMessage.getIPSpeed());
        }

        //将得到的IP存储在数据库中(每次先清空数据库)
        try {
            DataBaseDemo.delete();
            DataBaseDemo.add(ipMessages);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //从数据库中将IP取到
//        try {
//            List<IPMessage> iplist = DataBaseDemo.query();
//            for (IPMessage ip: iplist) {
//                out.println(ip.getIPAddress());
//                out.println(ip.getIPPort());
//                out.println(ip.getServerAddress());
//                out.println(ip.getIPType());
//                out.println(ip.getIPSpeed());
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
