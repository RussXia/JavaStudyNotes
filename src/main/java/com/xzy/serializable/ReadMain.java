package com.xzy.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("testForm.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        QueryTransportPriceForm testForm = (QueryTransportPriceForm) in.readObject();
        System.out.println(testForm);
        in.close();
    }
}
