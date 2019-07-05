package com.spring.sort;

/**
 * 快排
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{6, 3, 4,7, 9};
        QuickSort(array, 0, array.length - 1);
        for (int temp : array) {
            System.out.println(temp);
        }
    }

    public static void QuickSort(int[] array, int left, int right) {
        if (left >right)
            return;
        int r=right;
        int i=left;
        while (i < r) {
            while (array[i] < array[left]) {
                i++;
            }
            while (array[r] > array[left]) {
                r--;
            }
            if (i < r) {
                //交换left和right的位置
                int temp = array[i];
                array[i] = array[r];
                array[r] = temp;
            }
        }
        if (i < r) {
            //交换位置
            int temp=array[left];
            array[left] = array[r];
            array[r] = temp;
        }
        QuickSort(array, left, r - 1);
        QuickSort(array, r +1, right);
    }

}
