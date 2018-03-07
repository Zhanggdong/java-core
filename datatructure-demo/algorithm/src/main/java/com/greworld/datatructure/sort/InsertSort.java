package com.greworld.datatructure.sort;

/**
 * @author 风骚的GRE
 * @Description 直接插入排序：
 * 插入排序思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置，直到全部插入排序完为止
 * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止。
 * 比如： 58 68 59 52
 * 第一趟：68>58 不做处理
 * 第二趟：59与58,68比较，58<59<68,那么59插入58之后
 * 第三趟：52<58，那么52插入58之前
 * @date 2018/3/6.
 */
public class InsertSort {
    public static void main(String[] args) {
        // 待排序的数组
        int [] array = {58,68,45,52,33,59};
        System.out.println("排序前的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        insertSort(array);
        System.out.println("排序后的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

    public static void insertSort(int [] array){
        int temp;// 临时变量

        // 第一个元素不用排序，从第二个开始处理
        for (int i=1;i<array.length;i++){
            int j = i-1;// 比较的位置
            temp = array[i];
            for (;j>=0&&temp<array[j];j--){
                // 比temp大的数整体后移一个位置（找到插入的位置）
                array[j+1] = array[j];
            }
            // 插入元素
            array[j+1] = temp;
        }
    }
}
