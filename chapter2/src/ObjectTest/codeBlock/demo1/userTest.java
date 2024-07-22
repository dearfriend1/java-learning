package ObjectTest.codeBlock.demo1;

import com.sun.tools.javac.Main;

public class userTest {
    public static void main(String[] args) {
        User u1;
        u1 = new User();
        System.out.println(u1.getInfo());

        User u2 = new User("zhuhaihong","17723");
        System.out.println(u2.getInfo());
    }
}
