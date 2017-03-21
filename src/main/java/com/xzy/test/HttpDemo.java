package com.xzy.test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * code : akde
 * response : {success:true, message:"Thanks, now you can see the download URL."}
 * Created by RuzzZZ on 2017/3/21.
 */
public class HttpDemo {

    private static final String RESULT = "{success:true, message:\"%s is illegal!\"}";

    private static final String URL = "http://book.mvnsearch.org/person/invited.json?_dc=1490060108908&invitedCode=";

    public static void main(String[] args) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        //97-122
        char a = 'a';
        char b = 'd';
        char c = 'p';
        char d = 'g';
        do {
            String str = generateString(a, b, c, d);
            // 以get方式请求
            HttpGet httpGet = new HttpGet(URL + str);
            // 打印请求地址
            System.out.println("executing request "
                    + httpGet.getRequestLine().getUri());
            // 创建响应处理器处理服务器响应内容
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // 执行请求并获取结果
            String responseBody = httpclient.execute(httpGet, responseHandler);
            System.out.println("----------------------------------------");
            if (!String.format(RESULT,str).equals(responseBody)) {
                System.out.println("code : " + str);
                System.out.println("response : " + responseBody);
                break;
            }
            if (d < 'z')
                d++;
            else if (d == 'z') {
                if (c < 'z') {
                    c++;
                } else if (c == 'z') {
                    if (b < 'z') {
                        b++;
                    } else if (b == 'z') {
                        if (a < 'z') {
                            a++;
                        } else if (a == 'z') {
                            System.out.println("str is over");
                            break;
                        }
                        b = 'a';
                    }
                    c = 'a';
                }
                d = 'a';
            }
        } while (true);

    }

    public static String generateString(char a, char b, char c, char d) {
        String str = Character.toString(a) + Character.toString(b) + Character.toString(c) + Character.toString(d);
        return str;
    }
}
