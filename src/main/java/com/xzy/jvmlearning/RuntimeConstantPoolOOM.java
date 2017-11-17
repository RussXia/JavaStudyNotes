package com.xzy.jvmlearning;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms1m -Xmx1m -Xmn800k
 *
 * @author RuzzZZ
 * @since 17/11/2017 2:27 PM
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}