package ObjectTest.abstractTest.demo1Area;

public abstract class GeometricObject {

    static String notice  = "抽象类";


    //非抽象类————可以定义模版函数调用抽象方法
    public void printInfo(){
        System.out.println(notice+"被调用！");

        System.out.println(this.getClass()+"的面积："+findArea());   //调用了自己的抽象方法
    }

    public static void setNotice(String notice){
        GeometricObject.notice = notice;

    }
    public abstract double findArea();    //抽象类




}
