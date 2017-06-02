package com.xzy.util.encrypt;

import org.junit.Test;

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
