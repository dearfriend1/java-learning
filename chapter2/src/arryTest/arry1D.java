package arryTest;

import javax.naming.InitialContext;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class arry1D {
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);

        //数组的申明初始化
        double[] arr1;
        arr1 = new double[]{1.1,21.3,123};       //静态初始化——初始话和赋值同时进行

        String[] name = new String[3];         //动态初始化——引用数据类型只能用动态初始化
        for (int i = 0;i<name.length;i++){
            System.out.println("输入第"+(i+1)+"个name");
            name[i] = scan.next();
        }
        for (int i = 0;i<name.length;i++){
            System.out.println(name[i]);
        }

        System.out.println(5>>1);


        //线性查找——无规律
        int [] a = {2,33,5,5,111,5,44,33};
        int target = 33;
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i]==target) {
                System.out.println("index:" + i + "=" + target);
//                break;
            }
        }

        //二分查找——有序数组
        //head end middle方式进行

        //数组排序
        System.out.println("===================Bubble Sorted==============");
        //冒泡排序
        int len = scan.nextInt();
        int[] s1 =new int[len];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = (int)(Math.random()*100+1);
        }
        System.out.print("Initial:\t");
        for (int j : s1) {
            System.out.print(j + "\t");
        }
        System.out.println();


        //排序

        for (int k =  0; k<s1.length-1;k++) {
            for (int i = 0; i < s1.length - 1-k; i++) {
                if (s1[i] > s1[i + 1]) {
                    //交换值
                    int te = s1[i];
                    s1[i] = s1[i + 1];
                    s1[i + 1] = te;
                }
            }
            System.out.print((k+1)+"-th:\t");
            for (int j : s1) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.print("Sorted:\t");
        for (int j : s1) {
            System.out.print(j + "\t");
        }


























    }


}
