package com.xzy.okhttp;

import com.google.common.base.Preconditions;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class OkHttpGetExample {

    private static OkHttpClient client = new OkHttpClient();

    private static final String OK_HTTP_URL = "http://square.github.io/okhttp/";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String doGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        Preconditions.checkNotNull(response, "response is null");
        Preconditions.checkNotNull(response.body(), "response body is null");
        return response.body().string();
    }

    public static String doPost(String url,String json) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        Preconditions.checkNotNull(response, "response is null");
        Preconditions.checkNotNull(response.body(), "response body is null");
        return response.body().string();
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(doGet(OK_HTTP_URL));
        System.out.println(doPost(OK_HTTP_URL, StringUtils.EMPTY));
    }
}
