package com.xzy.demo;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.FieldLayout;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiazhengyue
 * @since 2020-05-21
 */
public class BooleanDemo {

    private boolean a = true;
    private boolean b = true;
    private static boolean c = true;
    private static boolean d = true;

    public static void main(String[] args) throws NoSuchFieldException {


//        FieldLayout first = ClassLayout.parseClass(BooleanDemo.class).fields().first();
//        FieldLayout last = ClassLayout.parseClass(BooleanDemo.class).fields().last();
//        System.out.println(first.offset());
//        System.out.println(last.offset());

        Unsafe unsafe = UnsafeUtils.getUnsafe();
        Field a = BooleanDemo.class.getDeclaredField("a");
        Field b = BooleanDemo.class.getDeclaredField("b");
        Field c = BooleanDemo.class.getDeclaredField("c");
        Field d = BooleanDemo.class.getDeclaredField("d");
        System.out.println(unsafe.objectFieldOffset(a));
        System.out.println(unsafe.objectFieldOffset(b));
        System.out.println(unsafe.staticFieldOffset(c));
        System.out.println(unsafe.staticFieldOffset(d));

//        byte byte1 = 1;
//        System.out.println(ClassLayout.parseInstance(byte1).toPrintable());
//
//        Byte byte2 = new Byte((byte) 127);
//        System.out.println(ClassLayout.parseInstance(byte2).toPrintable());

    }

}
