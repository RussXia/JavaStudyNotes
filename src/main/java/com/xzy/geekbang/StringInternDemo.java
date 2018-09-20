package com.xzy.geekbang;

/**
 * jdk version:1.8.0_161
 * JVM options：
 * -Xmx1m  -XX:+PrintGCDetails -XX:+PrintStringTableStatistics
 */
public class StringInternDemo {

    public static void main(String[] args) throws InterruptedException {
        String str1 = new String("test11323121321333333333fdfssqerqewqr35iutrewkthr324532hj423ngjewhgjhweth5j342ret" +
                "tretorewtikrewtkreoit43iu5oi543tkregergnrtnuthjrektretl4j5i35o5i4mti3treti34ji54jimtejt" +
                "tji34u543iu5o43u5ejkwrefmkejtjewktjktmrejktrkewlro23ikrdjtsdgtrt");
        String str2 = str1.intern();

        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str1 == str2);

        String s3 = new String("12") + new String("34");
        s3 = s3.intern();
        String s4 = "1234";
        System.out.println(s3 == s4);//true

    }
}
