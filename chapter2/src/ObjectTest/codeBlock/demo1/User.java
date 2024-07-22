package ObjectTest.codeBlock.demo1;

import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private String userName;
    static private String password;
    static private long registrationTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        User.password = password;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public User() {}

    //不妨在空参构造器中初始化，
    {
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();
        userName =""+System.currentTimeMillis();
        password = "123456";
    }




    public User(String userName, String password) {
        this.userName = userName;
        User.password = password;
    }

    public String getInfo(){
        return "用户名"+userName+",\t密码："+password+",\t注册事件："+registrationTime;
    }
}
