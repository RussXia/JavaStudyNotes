package com.xzy.sort;

/**
 * @author xiazhengyue
 * @since 2020-03-11
 */
public class SortUtils {

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] > arr[j]) {
                    SortUtils.exchange(arr, i, j);
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.exchange(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = getMiddle(arr, left, right);
            quickSort(arr, left, middle - 1);
            quickSort(arr, middle + 1, right);
        }
        return arr;
    }

    private static int getMiddle(int[] arr, int left, int right) {
        int temp = arr[left];   //取第一个数做基准数字
        while (left < right) {
            //找到一个右边比基准数小的
            while (left < right && arr[right] > temp) {
                right--;
            }
            arr[left] = arr[right];
            //找到一个左边比基准数大的
            while (left < right && arr[left] < temp) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }

    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
