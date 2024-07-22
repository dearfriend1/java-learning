package ObjectTest;

public class Phone {
    //属性
    String name;
    String number;

    static int Price = 6999;

    //方法

    public void call(){
        System.out.println("拨打电话");
    }

    public void sendMessage(String message){
        System.out.println("发送消息："+message);
    }
}
