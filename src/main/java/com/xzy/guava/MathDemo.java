package com.xzy.guava;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

import static java.lang.System.out;

/**
 * Created by RuzzZZ on 2017/3/22.
 */
public class MathDemo {

    /**
     * 本处只列举了IntMath这个Demo，还有LongMath和BigIntegerMath。
     * 还有个工具类：{@link com.google.common.math.DoubleMath}
     * 具体的一些细节，可以到guava的com.google.common.math包下查找
     * 这三个工具类的使用方法很相似，在此就不一一列举了
     *
     * @param args
     */
    public static void main(String[] args) {
        //gcd:greatest common divisor最大公约数
        out.println(IntMath.gcd(10,12));
        //检查是否溢出，如果溢出，则 throw new ArithmeticException("overflow");
//        out.println(IntMath.checkedAdd(Integer.MAX_VALUE,1));

        /**
         * DOWN：向零方向舍入（去尾法）
         * UP：远离零方向舍入
         * FLOOR：向负无限大方向舍入
         * CEILING：向正无限大方向舍入
         * UNNECESSARY：不需要舍入，如果用此模式进行舍入，如果返回结果有余留小数，直接抛出ArithmeticException
         * HALF_UP：向最近的整数舍入，其中x.5远离零方向舍入
         * HALF_DOWN：向最近的整数舍入，其中x.5向零方向舍入
         * HALF_EVEN：向最近的整数舍入，其中x.5向相邻的偶数舍入
         * {@link RoundingMode}
         */
        out.println(IntMath.divide(10,3, RoundingMode.CEILING));
        //取模运算
        out.println(IntMath.mod(10,8));
        //阶乘
        out.println(IntMath.factorial(3));
        //是否是2的幂
        out.println(IntMath.isPowerOfTwo(4));
        //3的2次方
        out.println(IntMath.pow(3,2));

    }
}
