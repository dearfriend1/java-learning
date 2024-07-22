package demo4class;

public class test {
    public static void main(String []args){
        Person p1 = new Person("zhuhaihong", 11);  //第一种构造器

        Person p2 = new Person("zhuhaihong1");//第二种构造器

        Person p3 = new Person();//第三种构造器

        System.out.println(p1.name+"--"+p1.age);
        System.out.println(p2.name+"--"+p2.age);
        System.out.println(p3.name+"--"+p3.age);

    }


}
