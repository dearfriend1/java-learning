package ObjectTest.inherentPerson;

public class PersonTest {
    public static void main(String[] args){
//        Man boy = new Man("lisi");          //可以调用父类构造器吗？
//        //boy.name = "zhuhaihong ";
//        boy.age = 12;
//        boy.height = 179;
//        boy.weight = 70;
//        boy.Sleep();
//        boy.shortHair();


        Person p1 = new Man("lisi");
        System.out.println(p1.name);
        System.out.println((p1 instanceof Man));

        Man p2 = (Man) p1;
        System.out.println(p2.name);



    }
}
