package com.xzy.guava;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

/**
 * {@link com.google.common.base.Throwables}
 * 异常检查和传播的工具类
 * Created by RuzzZZ on 2017/3/2.
 */
public class ThrowablesDemo {

    public static void main(String[] args) throws IOException, SQLException {
        try {
            doSomething();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Throwable t) {
            System.out.println(Throwables.getStackTraceAsString(t));
            System.out.println("====================================================");
            Throwables.getRootCause(t).printStackTrace();
//            Throwables.propagateIfInstanceOf(t, IOException.class);ss
//            throw Throwables.propagate(t);
        }
    }

    public static void doSomething() throws Exception {
        throw new IOException("Test Test");
    }
}
