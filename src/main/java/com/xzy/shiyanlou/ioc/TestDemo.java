package com.xzy.shiyanlou.ioc;

/**
 * @author RuzzZZ
 * @since 25/01/2018 10:38 AM
 */
public class TestDemo {

    private Shiyanlou s;

    public void set(Shiyanlou s) {
        this.s = s;
    }

    public Shiyanlou getS() {
        return s;
    }

    public void setS(Shiyanlou s) {
        this.s = s;
    }

    public void sayHello(String str) {
        System.out.println("Hello " + str);
    }

    @Override
    public String toString() {
        return "TestDemo{" +
                "s=" + s +
                '}';
    }
}