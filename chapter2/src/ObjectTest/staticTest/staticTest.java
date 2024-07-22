package ObjectTest.staticTest;

public class staticTest {

    public static void main(String[] args) {

        System.out.println(People.country);
        People p1 = new People();
        p1.name = "xiaoming";
        p1.height = 178;
        System.out.println(p1.country);

        People p2 = new People();
        p2.name = "Tom";
        p2.height = 190;
        System.out.println(p2.country);
//        p2.country = "CHI";
        System.out.println(People.country);
        System.out.println(p1.country);
        System.out.println(People.country);
        p1.methodTest();

        student s1 = new student();
        System.out.println(student.country);
        student.methodTest();

        student.country = "CH1";
        System.out.println(student.country);
        System.out.println(People.country);

    }





}
