package ObjectTest.inherentPerson;

public class  Person {
    String name = "zhangsan";
    int age;
    double height;
    double weight;

    public Person() {
        System.out.println("调用的父类构造器");
    }

    public Person(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public void Sleep(){
        System.out.println("SleepMethod!");
    }
}


