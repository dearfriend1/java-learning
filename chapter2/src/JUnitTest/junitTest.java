package JUnitTest;

import org.junit.Test;

import java.util.Scanner;

public class junitTest {


    String name = "zhuhaihong";


    @Test
    public void test() {
        System.out.println("helllo");



    }

    @Test
    public void test2() {
        System.out.println("nihao ");

    }


    public void testVar(String name) {
        System.out.println("nihao" + name);
    }

    @Test
    public void testInput() {
        o();
        String s = "ahsduifg";

    }



    private void o() {
        String name1 = "yaomengqi";

        testVar(name1);
        testVar("hzuhaihong");
    }
}
