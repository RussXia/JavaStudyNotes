package com.xzy.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuzzZZ on 2017/2/16.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CarSnapInfo carSnapInfo = new CarSnapInfo();
        carSnapInfo.setModelName("Test123111");
        carSnapInfo.setCarId(100L);

        List<CarSnapInfo> list = new ArrayList<>();
        list.add(carSnapInfo);

        QueryTransportPriceForm form = new QueryTransportPriceForm();
        form.setCarInfoList(list);
        form.setName("HaHaHa1");
        FileOutputStream fout = new FileOutputStream("testForm.txt");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(form);
        out.close();
    }
}
