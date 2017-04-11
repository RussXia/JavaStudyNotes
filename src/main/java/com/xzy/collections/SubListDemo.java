package com.xzy.collections;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.System.out;
/**
 * list的subList操作要慎用！！！！
 * 因为它返回的是一个list的引用。所以对该list的修改会直接影响原list
 * Created by RuzzZZ on 2017/3/17.
 */
public class SubListDemo {

    public static void main(String[] args) throws IOException {
//        List<String> list = new ArrayList<>();
//        list.add("qqqq1");
//        list.add("qqqq2");
//        list.add("qqqq3");
//        list.add("qqqq4");
//        list.add("qqqq5");
//        list.add("qqqq6");
//        list.add("qqqq7");
//        list.add("qqqq8");
//        List<String> subList = list.subList(0, list.size());
//        subList.clear();
//        subList.add("Hello1");
//        subList.add("Hello2");
//        subList.add("Hello3");
//        out.println(list.size());
//        out.println(subList.size());


        URL url = new URL("http://merge-3d.gxpan.cn/uploads/model/chara_board/338126b930c5aebe2584f7d164d4e02d/中国版-哪吒-樱花.zip");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream inputStream = conn.getInputStream();

        BufferedInputStream buff = new BufferedInputStream(inputStream);


        FileOutputStream fos = new FileOutputStream("中国版-哪吒-樱花.zip");
        byte[] b = new byte[1024];

        while((inputStream.read(b)) != -1){

            fos.write(b);

        }
    }

}