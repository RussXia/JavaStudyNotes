package com.xzy.serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuzzZZ on 2017/2/16.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CarSnapInfo carSnapInfo = new CarSnapInfo();
        carSnapInfo.setModelName("Test123");
        carSnapInfo.setNumber(1);

        List<CarSnapInfo> list = new ArrayList<>();
        list.add(carSnapInfo);

        QueryTransportPriceForm form = new QueryTransportPriceForm();
        form.setCarInfoList(list);
        form.setArriveLocationName("HaHaHa");
        FileOutputStream fout=new FileOutputStream("testForm.txt");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(form);
        out.close();

        FileInputStream fin=new FileInputStream("testForm.txt");
        ObjectInputStream in=new ObjectInputStream(fin);
        QueryTransportPriceForm testForm=(QueryTransportPriceForm) in.readObject();
        System.out.println(testForm);
        in.close();
    }
}
