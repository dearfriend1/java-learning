package ObjectTest.danliModel;

import ObjectTest.inherentPerson.Man;

public class danliTest {
    public static void main(String[] args) {

        Bank1 bank = Bank1.getBank();
//        Bank bank = new Bank();
        bank.info();

        Bank bank1 = Bank.getBank();
        bank1.info();


    }
}
//饿汉式单例模式穿件
class Bank1{
    //1.私有化构造器
    private Bank1(){};

    //2.类内部创建类对象
    //4.设置为静态成员
    private static Bank1 bank = new Bank1();

    //3.静态方法返回属性对象
    public static Bank1 getBank(){
        return bank;
    }

    public void info(){
        System.out.println("饿汉式单例模式创建成功");
    }

}
//懒汉式单例模式

class Bank{
    //1.构造器私有化
    private Bank(){};

    //私有化类属性
    //只声明不创建，类加载不使用对象的时候不创建空间占内存
    static private Bank bank;

    //3.调用类方法得到对象
    public static Bank getBank(){
        if (bank == null){
            bank = new Bank();
        }
        return bank;
    }

    public void info(){
        System.out.println("懒汉式单例模式创建成功");
    }
}