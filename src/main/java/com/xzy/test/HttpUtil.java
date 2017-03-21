package com.xzy.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by RuzzZZ on 2017/3/21.
 */
public class HttpUtil {

    public static String get(String url)
            throws ClientProtocolException, IOException {
        // 创建一个默认的HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        try {
            // 以get方式请求
            HttpGet httpGet = new HttpGet(url);
            // 打印请求地址
            System.out.println("executing request "
                    + httpGet.getRequestLine().getUri());
            // 创建响应处理器处理服务器响应内容
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // 执行请求并获取结果
            String responseBody = httpclient.execute(httpGet, responseHandler);
            System.out.println("----------------------------------------");
            return responseBody;
        } finally {
            // 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
            httpclient.getConnectionManager().shutdown();
        }
    }
}
