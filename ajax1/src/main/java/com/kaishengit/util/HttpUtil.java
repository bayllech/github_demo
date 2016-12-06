package com.kaishengit.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 帅比 on 2016/12/6.
 */
public class HttpUtil {
    public static String sendHttpGetRequest(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse resp = httpClient.execute(httpGet);
            InputStream input = null;
            if (resp.getStatusLine().getStatusCode() == 200) {
                input = resp.getEntity().getContent();
                String result = IOUtils.toString(input, "UTF-8");
                httpClient.close();
                return result;
            } else {
                throw new RuntimeException("请求url: " + url + "异常 : " + resp.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("请求：" + url + " 异常 ");
        }
    }
}
