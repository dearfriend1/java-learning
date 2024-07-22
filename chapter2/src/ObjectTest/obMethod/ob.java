package ObjectTest.obMethod;




public class ob {



    public static void main(String[] args) {
//        Object o1 = new Object();
//        Object o2 = new String();
//        System.out.println(o2.getClass());

//
//        //clone()  Protect
//        Person p1 = new Person("Helen",12);
//        System.out.println(p1.toString());
//        System.out.println(p1);
//
//        Person p2 = p1.clone();
//        System.out.println(p2);

        //equals
        Person p1 = new Person("zhuhaihong",17);
        Person p2 = new Person("zhuhaihong",17);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.equals(p2));

        System.out.println(p1.toString());


    }
}
