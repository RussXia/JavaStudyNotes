package com.xzy.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;

public class JDReceiveCoupon {

    private static final String JD_URL = "http://api.m.jd.com/client.action?functionId=newBabelAwardCollection";


    private static final String PARAMS = "adid=99226FE6-8DB5-4802-8504-9A44BCB8DFF7&area=15_1213_1214_0&body=%7B%22cpId%22%3A%2211938194%22%2C%22actKey%22%3A%2211938194%22%2C%22activityId%22%3A%22Z1YNHWkidMDXRYZqgXqZd8iXv1R%22%2C%22scene%22%3A%221%22%2C%22args%22%3A%22key%3Dc3b67f7ff3b54bac926b665297cdbce6%2CroleId%3D11938194%22%2C%22moduleId%22%3A%22%22%7D&build=164680&client=apple&clientVersion=7.0.2&d_brand=apple&d_model=iPhone10%2C1&isBackground=N&lang=zh_CN&networkType=wifi&networklibtype=JDNetworkBaseAF&openudid=ad42ec3463ac0db78512ce47722a49bdc76dfde9&osVersion=11.3.1&partner=apple&screen=750%2A1334&sign=89f9c989110237e6571303d1c3357380&st=1526631510647&sv=120&uuid=coW0lj7vbXVin6h7ON%2BtMNFQqYBqMahr&wifiBssid=74018979f074bed19b6e6e7d242de3c6";


    public static void main(String[] args) throws InterruptedException {

        Long now = System.currentTimeMillis();

        LocalDateTime localDateTime = LocalDateTime.of(2018, Month.MAY, MonthDay.now().getDayOfMonth(), 19, 59, 59, 960);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
//        System.out.println(localDateTime.format(formatter));
//        System.out.println(Timestamp.valueOf(localDateTime).getTime() - now);
        Long tempTime = Timestamp.valueOf(localDateTime).getTime() - now;
        if (Timestamp.valueOf(localDateTime).getTime() > now) {
            Thread.sleep(Timestamp.valueOf(localDateTime).getTime() - now);
        }
        while (true) {
            String result = doPost(PARAMS);
            if (StringUtils.isNotBlank(result) && result.contains("成功"))
                break;
            Thread.sleep(100);
        }
    }

    public static String doPost(String params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(JD_URL);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Cookie", "pin=jd_79cf9dc717491;wskey=AAFa_W_aAECNmizfbF1xCOyk9JMg1VrkcuxlBpW7VQJ-oOGw7nxMmAcTwDBRvrxzj28SEgjRlwBPWsq9_mMIy90v4Tts4op3;whwswswws=17face913d5514c55aaf712448e378a16e272114e18cb1f6759fbe21a6");
        httpPost.setHeader("User-Agent", "JD4iPhone/164680 (iPhone; iOS 11.3.1; Scale/2.00)");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();

            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                return "请求失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return StringUtils.EMPTY;
    }
}
