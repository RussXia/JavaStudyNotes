package com.xzy.shiyanlou;

import java.io.*;

/**
 * Created by RuzzZZ on 2017/5/17.
 */
public class FileOperate {

    public String readFile(String pathName) throws IOException {
        File file = new File(pathName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufw = new BufferedReader(fileReader);
        String resultStr = "";
        String result;
        while ((result=bufw.readLine())!=null){
            resultStr += result;
        }
        return resultStr;
    }

    public void outFile(String pathName, String results) throws IOException {
        File file = new File(pathName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(results);
        fileWriter.close();
    }
}
