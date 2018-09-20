package com.xzy.serializable;

import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HessianSerializableDemo {

    public static void main(String[] args) throws IOException {
        CarSnapInfo carSnapInfo = new CarSnapInfo();
        carSnapInfo.setModelName("Test123111");
        carSnapInfo.setCarId(100L);
        carSnapInfo.setDate1(new java.util.Date());
        carSnapInfo.setDate3(new java.sql.Date(1514736000000L));

//        List<CarSnapInfo> list = new ArrayList<>();
//        list.add(carSnapInfo);
//
//        QueryTransportPriceForm form = new QueryTransportPriceForm();
//        form.setCarInfoList(list);
//        form.setName("HaHaHa1");

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //Hessian的序列化输出
        HessianOutput ho = new HessianOutput(os);

        ho.writeObject(carSnapInfo);

        byte[] formByte = os.toByteArray();
        FileOutputStream fout = new FileOutputStream("testForm2.txt");
        fout.write(formByte);
        System.out.println(carSnapInfo);


    }
}
