package com.xzy.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.InflaterOutputStream;

public class ZLibDemo {

    /**
     * zlib解压+base64
     */
    public static byte[] decompressData(byte[] encdata) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InflaterOutputStream zos = new InflaterOutputStream(bos);
            zos.write(encdata);
            zos.close();
            return bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("zlib.bin");
        byte[] b = new byte[1024*64];
        in.read(b);
        for (int i = 0; i < 666; i++) {
            b = decompressData(b);
        }
        byte[] result = b;
        System.out.println(JSONObject.toJSONString(result));
        System.out.println(new String(result));
        byte[] decodeResult = Base64.decodeBase64(result);
        System.out.println(JSONObject.toJSONString(decodeResult));
    }

}
