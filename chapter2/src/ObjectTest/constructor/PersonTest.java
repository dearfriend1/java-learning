package ObjectTest.constructor;

public class PersonTest {
    public static void main(String[] args){
        Person p1 = new Person();
        p1.getInfo();

        Person p2 = new Person("zhuhaiong");
        p2.getInfo();

        Person p3 = new Person("zhuhaiong",123);
        p3.getInfo();

    }
}
