package com.xzy.algorithm;

public class JavaBasicInfo {

    public static void exercise111() {
        System.out.println("The exercise 1.1.1 ------");
        int a = (0 + 15) / 2;
        double b = 2.0e-6 * 1000000000.1;
        boolean c = true && false || true && true;

        System.out.println("the value of a:" + a);
        System.out.println("the value of b:" + b);
        System.out.println("the value of c:" + c);

    }

    public static void exercise112() {
        System.out.println("The exercise 1.1.2 ------");
        double a = (1 + 2.236) / 2;
        double b = 1 + 2 + 3 + 4.0;
        System.out.println(a);
        System.out.println(b);
    }


    /**
     * Convert an integer to a string of bin
     * ary representation
     *
     * @param N the int value
     * @return the binary representation string
     */
    public static String e119(int N) {
        String bStr = "";

        while (N > 0) {
            N = N / 2;
            if (N % 2 == 0) {
                bStr += "0";
            } else {
                bStr += "1";

            }
        }
        return bStr;

        /*
            Reference implementation in the book
            String s = "";
            for(int n=N;n>0;n/=2){
              s = (n%2) + s;
            }
         */

    }

    public static void exercise113(String[] args) {
        System.out.println("The exercise 1.1.3 ------");
        if (args.length == 3) {
            if (Integer.parseInt(args[0]) == Integer.parseInt(args[1]) &&
                    Integer.parseInt(args[1]) == Integer.parseInt(args[2])) {
                System.out.println("equal");
            } else {
                System.out.println("not equal");
            }
        } else {
            System.out.println("not equal");
        }

    }

    public static void exercise115(double i) {
        if (i > 0 && i < 1) {
            System.out.println("true");
        } else
            System.out.println("false");

    }

    public static void exercise116() {
        int f = 0, g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    public static void exercise117() {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        System.out.printf("%.5f\n", t);
    }

    public static int mystery(int a, int b) {
        if (b == 0)
            return 0;
        if (b % 2 == 0)
            return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static void e1111(boolean[][] value) {
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                System.out.println("row:" + i + ",col:" + j + " " + (value[i][j] ? "*" : " "));
            }
        }

    }

    public static void e1112() {
        int[] a = new int[10];

        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }

        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    // exercise 1.1.15
    public static int[] histogram(int[] a, int M) {
        int[] b = new int[M];


        for (int i = 0; i < b.length; i++) {
            int n = 0; // the counter
            for (int j = 0; j < a.length; j++) {
                if (i == a[j])
                    n++;
            }

            b[i] = n;
        }

        return b;

    }

    public static void e1113() {
        int M = 3;
        int N = 2;

        int[][] a = {{4, 2}, {1, 2}, {3, 9}};
        int[][] b = new int[N][M];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[j][i] = a[i][j];
            }
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Get a max int value that not greater than log2(N)
     *
     * @param N the number
     * @return the max int value
     */
    public static int lg(int N) {
        int max = 0;

        while ((N = N / 2) >= 1) {
            max++;
        }

        return max;
    }

    public static void testLg() {
        System.out.println("Testing lg() method with N=39");
        System.out.println(lg(39));
    }

    public static void testHistogram() {
        System.out.println("Testing histogram method with M=10");
        int[] b = histogram(new int[]{1, 3, 4, 6, 8, 1}, 10);
        int sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum += b[i];
        }
        if (sum == 6)
            System.out.println("OK to proceed for 1.1.15");
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;

    }

    public static long Nx(int N) {
        if (N == 0) return 1;
        if (N == 1) return 1;
        return N * Nx(N - 1);
    }

    // it's a wrong implementation
    public static double ln(double x) {

        ///10*10*10 = 1000
        ///        ln(1000) =3

        double result = 0.0;

        while ((x /= 10) >= 1) {
            result++;
        }

        System.out.println("remainder is:" + x);

        //result += ;

        return result;
    }

    public static String mystery(String s) {
        int N = s.length();
        if (N <= 1) {
            return s;
        }
        return mystery(s.substring(0, N / 2)) + mystery(s.substring(N / 2, N));
    }


    public static void main(String[] args) {
//        exercise111();
//        exercise112();

//        exercise113(args);
//        exercise115(1.4);
//        exercise116();
//        exercise117();

        System.out.println(mystery(2, 25));

        System.out.println(e119(18));
        e1111(new boolean[][]{{true, false}, {false, true}, {false, false}});

        e1112();

        e1113();

        testLg();
        testHistogram();

        System.out.println("exR1(6)" + exR1(6));

        System.out.println(ln((double) Nx(10)));

        System.out.println(Math.log10(Nx(10)));


        System.out.println(mystery("Hello World"));
    }
}
