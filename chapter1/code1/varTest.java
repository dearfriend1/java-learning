package code1;

import java.util.Scanner;

public class varTest {

    public static void main(String[] arg){

        int a;
        a = 8;

        //字符类型
        char c1 = 'a';  //直接字符
        char c2 = '\u0043';      //unicode值,四个16进
        char c3 = '\n';//转义字符

        System.out.println("var_value:"+ a);

        System.out.println("c1_var_value:"+ c1);
        System.out.println("c2_var_value:"+ c2);
        System.out.println("a"+c3+"c");


        //数据类型转换

        //强制类型转化
        int cd  = 128;
        byte b  = (byte) cd;
        System.out.println("b"+b);

        //String
        boolean bo = true ;
        String s1 = "bo is ";
        System.out.println(s1+bo);

        //进制
        int num1 = 123;   //十进制
        int num2 = 0b1110;//二进制
        int num3 = 0234;    //八进制
        int num4 = 0x7d8f;   //十六进制
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);

        //运算符
        boolean b1 = "hello" instanceof String;
        System.out.println(b1);


        //Scanner类
        System.out.println("input condition1:");
        Scanner scan = new Scanner(System.in);
        int condition1 = scan.nextInt();
        scan.close();
        System.out.println(condition1);
        int ran  = (int) (Math.random()*101);
        System.out.println(ran);



        //流程控制
        //分支结构ifelse-switch case
        switch (ran%10){
            case 1:
                System.out.println("余1");
                break;
            case 2:
                System.out.println("余2");
                break;
            case 3:
                System.out.println("余3");
                break;
            default:
                System.out.println("other");

        }





        //循环for while do-while JDK5.0for-each
        System.out.println("产生10个随机数");
        for (int i = 0; i<10;i++){
            System.out.print((int)(Math.random()*101)+"\t");
        }
        System.out.println();

        //水仙花数
        System.out.print("水仙花数有：");
        for (int i=100;i<1000;i++){
            int anum1= i / 100;
            int anum2 = i / 10 % 10;
            int anum3 = i % 10;
            if (i == anum3*anum3*anum3+anum2*anum2*anum2+anum1*anum1*anum1)
                System.out.print(i+"\t");
        }
        System.out.println();
        System.out.println("========================");

        //while
        System.out.println("while 循环");
        int j = 1;
        while(j<=10){
            //j++;
            System.out.print(j);
            //j++;
            ++j;
        }

        //do-while
        int k = 0;
        do {
            System.out.print(k);
            k++;
        }while (k<10);

        System.out.println();
        int m = 0;
        for (;;){
            if (m  == 10)
                break;

            System.out.println(m);
            m++;
        }





    }

}
