package com.greworld.datatructure.sort;

/**
 * @author 风骚的GRE
 * @Description 希尔排序：
 * 基本思想：将数的个数设为n，取奇数k=n/2，将下标差值为k的书分为一组，构成有序序列。
 * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
 * 重复第二步，直到k=1执行简单插入排序。
 * @date 2018/3/7.
 */
public class HillSort {
    public static void main(String[] args) {
        int[] array = {57,68,59,52,72,28,96,33,24,19};
        System.out.println("排序前的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        hillSort(array);
        System.out.println("排序后的数组：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }

    private static void hillSort(int[] array) {
        int len = array.length;// 数组长度
        int d = len;// 步长
        while (true){
            d = d/2;
            for (int i = 0;i<d;i++){// 需要排序的左侧部分：分组数
                // 分组完成，需要使用直接插入排序来处理
                for (int j = i+d;j<len;j++){// 组中的元素，从第二个开始排序
                    int temp = array[j];
                    int k = j-1;
                    for (;k>=0&&temp<array[k];k--){
                        array[k+1]=array[k];
                    }
                    // 插入元素
                    array[k+1] = temp;
                }
            }
            // 当步长为1 时排序结束
            if (d == 1){
                break;
            }
        }

    }
}
