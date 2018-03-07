package com.greworld.datatructure.sort;

/**
 * @author 风骚的GRE
 * @Description 简单选择排序：
 * 基本思想：遍历整个序列，将最小的数放在最前面。
 * 遍历剩下的序列，将最小的数放在最前面。
 * 重复第二步，直到只剩下一个数。
 * @date 2018/3/7.
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {32,43,23,19,45,5,9};
        System.out.println("排序前的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        directSelectionSort(array);
        System.out.println("排序后的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 如何写代码：
     * （1）首先确定循环次数，并且记住当前数字和当前位置。
     * （2）将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
     * （3）比对完成后，将最小的值与第一个数的值交换。
     * 重复2、3步。
     * @param array
     */
    private static void directSelectionSort(int[] array) {
        int len = array.length;//数组长度
        for (int i=0;i<len;i++){// 循环次数
            int key = array[i];
            int position = i;// 最小位置
            int j = i+1;// 比较的开始元素位置
            for (;j<len;j++){
                if (array[j]<key){
                    key = array[j];
                    position = j;
                }
            }
            // 交换
            array[position] = array[i];
            array[i] = key;
        }
    }
}
