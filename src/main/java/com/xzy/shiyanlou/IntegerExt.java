package com.xzy.shiyanlou;

/**
 * @author RuzzZZ
 * @since 25/01/2018 10:08 AM
 */
public class IntegerExt {

    public Integer i;

    public static IntegerExt getInstance(int i) {
        IntegerExt integerExt = new IntegerExt();
        integerExt.i = Integer.valueOf(i);
        return integerExt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerExt)) {
            return false;
        }
        return this.i.equals((Integer) ((IntegerExt) obj).i);
    }

    public static void main(String[] args) {
        IntegerExt i1 = IntegerExt.getInstance(1);
        IntegerExt i2 = IntegerExt.getInstance(1);
        IntegerExt i3 = IntegerExt.getInstance(1111);
        IntegerExt i4 = IntegerExt.getInstance(1111);

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));
    }
}