package demo4class;


//构造方法重构
public class Person {
    String name;
    int age;

    //构造方法
    public Person(String n,int a){
        name = n;
        age = a;
    }

    //引用另一构造器重构构造器
    public Person(String n){
        this(n,20);      //此处，传入了一个参数，使用this引用了第一个构造器
    }

    public Person(){
        this("unkown"); //此处，引用了第二个构造器，默认传入name，年龄则为构造器中的默认20
    }






}
