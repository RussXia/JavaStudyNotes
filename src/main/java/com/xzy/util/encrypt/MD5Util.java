package com.xzy.util.encrypt;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RuzzZZ on 2017/6/2.
 */
public class MD5Util {

    //md5预定义好的salt
    private final static String KEY = "xzy199400@gmail.com!";

    /**
     * 获取简单MD5值
     * @param data
     * @return
     */
    public static String simpleStringMD5(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = data.getBytes(Charset.forName("UTF-8"));
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 获取MD5值(加盐)
     * @param data
     * @return
     */
    public static String stringMD5(String data) {
        return Md5WithSalt(data, KEY);
    }


    /**
     * 获取自定义盐的MD5值
     * @param data
     * @param customSalt
     * @return
     */
    public static String customMD5(String data, String customSalt){
        return Md5WithSalt(data, customSalt);
    }

    /**
     * 获取加盐MD5
     * @param data
     * @param salt
     * @return
     */
    private static String Md5WithSalt(String data, String salt){
        data = salt + data;
        return simpleStringMD5(data);
    }

    /**
     * 字节数组转16进制
     * @param byteArray
     * @return
     */
    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f' };
        char[] resultCharArray =new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(MD5Util.simpleStringMD5("hello1234"));
        System.out.println(MD5Util.stringMD5("hello world"));
        System.out.println(MD5Util.customMD5("hello1234","salt"));
        String data = "hello world";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] inputByteArray = data.getBytes(Charset.forName("UTF-8"));
        messageDigest.update(inputByteArray);
        byte[] resultByteArray = messageDigest.digest();
        String result = new String(resultByteArray);
        System.out.println(JSONObject.toJSONString(resultByteArray));
    }
}
