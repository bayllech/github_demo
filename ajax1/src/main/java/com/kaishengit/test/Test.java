package com.kaishengit.test;

import com.kaishengit.util.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 帅比 on 2016/12/6.
 */
public class Test {
    public static void main(String[] args) throws IOException {
   /*     CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.kaishengit.com");
        try {
            HttpResponse resp = httpClient.execute(httpGet);
            int code = resp.getStatusLine().getStatusCode();
            if (code == 200) {
                InputStream input = resp.getEntity().getContent();
                String result = IOUtils.toString(input, "utf-8");

                System.out.println(result);
            } else {
                throw new RuntimeException("请求异常 : " + code);
            }
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException("请求服务器异常", e);
        }
*/
        String result = HttpUtil.sendHttpGetRequest("http://www.kaishengit.com");
        System.out.println(result);

   /*    CloseableHttpClient httpClient = HttpClients.createDefault();
       HttpGet httpGet = new HttpGet("https://avatars0.githubusercontent.com/u/22362346?v=3&u=32f8559325c7955972381b99646ecd7ce75abb18&s=400");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream input = response.getEntity().getContent();
                FileOutputStream out = new FileOutputStream("D:/upload/x.jpg");
                IOUtils.copy(input, out);
                out.flush();
                out.close();
                input.close();
            }
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException("请求异常", e);
        }*/

  /*      CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/save");
        try {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", "name"));
            params.add(new BasicNameValuePair("address", "usa"));

            httpPost.setEntity(new UrlEncodedFormEntity(params));
            for (int i = 0;i<5;i++) {
                httpClient.execute(httpPost);
            }
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException("访问服务器异常", e);
        }
*/

    }
}
