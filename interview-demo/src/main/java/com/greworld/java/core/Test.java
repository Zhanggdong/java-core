package com.greworld.java.core;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class Test
{
    public static Test t1 = new Test();
    {
        System.out.println("blockA");
    }
    static
    {
        System.out.println("blockB");
    }
    public static void main(String[] args)
    {
        byte b1=1,b2=2,b3,b6,b8;
        final byte b4=4,b5=6,b7;
        //b3=(b1+b2);  /*语句1*/
        b6=b4+b5;    /*语句2*/
        //b8=(b1+b4);  /*语句3*/
        //b7=(b2+b5);  /*语句4*/
        //System.out.println(b3+b6);
        Test t2 = new Test();
        int[][] arrays = {{1,2,3,4},{2,3,4,5}};
        System.out.println(Find(4,arrays));
    }


    public static boolean Find(int target, int [][] array) {
        int m=array.length;
        int n=array[0].length;
        int row=m-1;
        int column=0;
        if(m==0&&n==0){
            return false;
        }
        while(row>=0&&column<n){
            if(target>array[row][column])
                column++;
            else if (target<array[row][column])
                row--;
            else
                return true;
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        /****************最简单的解答方式***************/
    	String str1=str.toString();
        String str2=str1.replace(" ","%20");
        return str2;
        /*******************解答方式二************************/
    }

}
