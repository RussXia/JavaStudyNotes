package com.xzy.encrypt;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RuzzZZ on 2017/6/2.
 */
public class AESUtilTest {

    @Test
    public void testEncrypt() throws Exception {
        AESUtil aesUtil = new AESUtil(AESUtil.DEFAULT_KEY);
        String encryptRes = aesUtil.encrypt("Hello World!");
        System.out.println(encryptRes);
    }


    @Test
    public void testDecrypt() throws Exception {
        AESUtil aesUtil = new AESUtil(AESUtil.DEFAULT_KEY);
        String decryptRes = aesUtil.decrypt("Jj3zKTeNzLSgqVUUVCOB8g==");
        System.out.println(decryptRes);
    }
}
