package com.xzy.serializable;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HessianSerializer {

    public byte[] serialize(Object obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        try {
            ho.writeObject(obj);
            ho.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return os.toByteArray();
    }


    public <T> T deserialize(byte[] bytes, Class<T> cls) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input hi = new Hessian2Input(is);
        try {
            return (T) hi.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Byte type = 1;
        Assert.assertTrue(type == Byte.valueOf("1"));
        HessianSerializer hessianSerializer = new HessianSerializer();
        byte[] serialResult = hessianSerializer.serialize(type);
        Byte deSerialType = hessianSerializer.deserialize(serialResult, Byte.class);
        System.out.println(deSerialType.hashCode());
        System.out.println(type.hashCode());
        Assert.assertTrue(deSerialType == type);
    }

}
