package ObjectTest;

public class phoneTest {
    public static void main(String[] args){

        //创建类对象
        Phone phone1 = new Phone();

        //对象调用
        phone1.name  = "huawei";
        phone1.number = "121234123412";
        System.out.println(phone1);    //ObjectTest.Phone@30f39991(包+类)

        phone1.call();
        phone1.sendMessage("ahahhhah");
        Phone p2 = new Phone();
        p2 = phone1;
        p2.name = "apple";
        System.out.println();
        System.out.println(Phone.Price);



    }


}
