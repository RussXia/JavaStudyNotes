package com.xzy.sort;

/**
 * @author xiazhengyue
 * @since 2020-03-11
 */
public class SortDemo {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 1, 9};
        SortUtils.quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

}
