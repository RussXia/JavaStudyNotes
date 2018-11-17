package com.xzy.util.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by RuzzZZ on 2017/6/2.
 */
public class AESUtil {

    //密钥长度的最少支持为128、192、256,对应字符串长度为16,24,32
    public static final String DEFAULT_KEY = "xzy199400@gmail!";

    private static String DEFAULT_CHARSET = "UTF-8";
    private static String WORK_MODE = "AES/CBC/PKCS5Padding";
    private static String RANDOM = "SHA1PRNG";
    private static String ALGORITHM = "AES";

    private SecretKeySpec secretKeySpec;

    public AESUtil(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SecureRandom random = SecureRandom.getInstance(RANDOM);
        random.setSeed(key.getBytes(DEFAULT_CHARSET));
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
        kgen.init(key.length() * 8, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        this.secretKeySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
    }

    public String encrypt(String content) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(WORK_MODE);// 创建密码器
        byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(secretKeySpec.getEncoded());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);// 初始化
        byte[] encrypted = cipher.doFinal(byteContent);
        //base64转码，方便网络间传输
        return Base64.encodeBase64String(encrypted);
    }

    public String decrypt(String content) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(WORK_MODE);// 创建密码器
        IvParameterSpec ivParameterSpec = new IvParameterSpec(secretKeySpec.getEncoded());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);// 初始化
        //先base64转码
        byte[] encrypted1 = Base64.decodeBase64(content);
        //解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, "UTF-8");
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException, NoSuchPaddingException, InvalidKeyException {
        AESUtil aesUtil = new AESUtil("cmVhZHkgdG8gZ28=");
        String content = aesUtil.encrypt("helloworld");
        System.out.println(content);
    }
}
