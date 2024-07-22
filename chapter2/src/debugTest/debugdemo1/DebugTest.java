package debugTest.debugdemo1;

import org.junit.Test;

public class DebugTest {

    @Test
    public void  test(){
        int age = 34;
        String name1 = "zhuhaihong";

        Student s1 = new Student();

        s1.setName(name1);
        s1.setAge(age);
        System.out.println(s1.getInfo());
        System.out.println(s1.getClass());

    }
}
