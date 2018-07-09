package com.xzy.serializable;

import com.caucho.hessian.io.HessianInput;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HessianSeriaReadDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("testForm2.txt");
        byte[] formByte = new byte[2048];
        fin.read(formByte);
        ByteArrayInputStream is = new ByteArrayInputStream(formByte);
        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        QueryTransportPriceForm priceForm = (QueryTransportPriceForm) hi.readObject();
        System.out.println(priceForm);
    }
}
