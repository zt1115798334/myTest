package com.zt;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class Phone implements Runnable {
    @Override
    public void run() {
        getPhoneAddr();
    }

    public void getPhoneAddr() {
        try {
            String path = System.getProperty("user.dir");
            FileWriter fileWriter = new FileWriter(path + "/output.vcf");
            BufferedWriter out = new BufferedWriter(fileWriter);
            StringBuilder httpArg = new StringBuilder("137");
            Integer count = 0;

            for (int i = 0; i < 9999; i++) {
//                Thread.sleep(100);
                getPhones(httpArg, i);

                for (int j = 0; j < 9999; j++) {
                    StringBuilder sb2 = new StringBuilder(httpArg);
                    getPhones(sb2, j);
                    System.out.println("开始判断号码 = " + sb2.toString());
                    String xiao = getPhoneInfo2(sb2.toString());
                    if (xiao != null && (xiao.contains("保定") || xiao.contains("石家庄"))) {
                        String name = "999" + count;
                        StringBuilder sb = new StringBuilder();
                        sb.append("BEGIN:VCARD\n")
                                .append("VERSION:3.0\n")
                                .append("N:;").append(name).append(";;;\n")
                                .append("FN:").append(name).append("\n")
                                .append("TEL;TYPE=PREF,CELL:").append(sb2.toString()).append("\n")
                                .append("PRODID:ez-vcard 0.9.7-RC02\n")
                                .append("END:VCARD\n");
                        out.write(sb.toString());
                        out.flush(); // 把缓存区内容压入文件
                        System.out.println("添加号码" + sb2.toString());
                        count++;
                    } else {
                        System.out.println("xiao = " + xiao);
                    }
                }


            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("count = " + count);
            out.close(); // 最后记得关闭文件
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void getPhones(StringBuilder httpArg, int i) {
        if (i >= 1000) {
            httpArg.append(i);
        } else if (i >= 100) {
            httpArg.append("0").append(i);
        } else if (i >= 10) {
            httpArg.append("00").append(i);
        } else {
            httpArg.append("000").append(i);
        }
    }

    public static String getPhoneInfo(String phone) throws UnsupportedEncodingException {
        Map<String, String> map = Maps.newHashMap();
        map.put("mobile", phone);
        map.put("action", "mobile");
        String xiao = "http://www.chachahaoma.com/s/" + phone + ".html";
        String html = pickGetData(xiao, map, "UTF-8");
        String gStr = analyzeWebSiteName(html);
        return gStr;
    }

    public static String getPhoneInfo2(String phone) throws UnsupportedEncodingException {
        Map<String, String> map = Maps.newHashMap();
        map.put("q", phone);
        String xiao = "https://shouji.51240.com/" + phone + "__shouji/";
        String html = pickGetData(xiao, map, "UTF-8");
        String result = analyzeWebSiteName2(html);
        System.out.println("result = " + result);
        return result;
    }

    /*
     * 爬取网页信息(post)
     */
    private static String pickGetData(String url, Map<String, String> map, String charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = Lists.newArrayList();
            map.forEach((key, value) -> {
                list.add(new BasicNameValuePair(key, value));
            });
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
//                httpGet.setEntity(entity);
            }
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String pickPostData(String url, Map<String, String> map, String charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
//            HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = Lists.newArrayList();
            map.forEach((key, value) -> {
                list.add(new BasicNameValuePair(key, value));
            });
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String analyzeWebSiteName(String html) {
        Document document = Jsoup.parse(html);
        Element resutltData = document.getElementById("txtProvince");
        if (resutltData != null) {
            List<Node> listNode = resutltData.childNodes();
            String xiao = listNode.get(0).outerHtml();
            System.out.println(xiao);
            return xiao;
        } else {
            return null;
        }

    }

    private static String analyzeWebSiteName2(String html) {
        String string = null;
        Document document = Jsoup.parse(html);
        Elements resutltData = document.getElementsByClass("kuang_biaoge");
        if (resutltData != null && resutltData.size() != 0) {
            Element table = resutltData.get(0);
            Element tbody = table.children().get(0);
            Element tr = tbody.children().get(0);
            Element td = tr.children().get(0);
            Element table2 = td.children().get(0);
            Element tbody2 = table2.children().get(0);
            Element tr2 = tbody2.children().get(1);
            Element td2 = tr2.children().get(1);
            string = td2.childNode(0).outerHtml();
            return string;
        } else {
            return null;
        }

    }

}
