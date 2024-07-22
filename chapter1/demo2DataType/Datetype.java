package demo2DataType;

import java.util.Scanner;

/**
 * 本类主要是对一些数据类型 的联系
 * @ version 1.0  2024.3.2
 * @ author zhuhaihong
 */






public class Datetype {

    public static void main(String []args){

      System.out.println("Please input your data！");

      String str = "";
        Scanner scan = new Scanner(System.in);
//        str+=  scan.next();      //只会读取到分割符前
//        str+=  scan.next();      //只会读取到分割符前
//        str+=  scan.next();      //只会读取到分割符前
        str+=  scan.nextLine();
        System.out.println("your input data is:"+str);


    }


}
