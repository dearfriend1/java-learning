package ObjectTest.constructor;

public class  Person {
    private String name;
    private double salary;

    //构造器
    public Person(){
    }

        Person(String name){
        this.name  = name;
        salary = 5000;
    }
    public Person(String name,double salary){
        this.name = name;
        this.salary = salary;
    }

    public void getInfo(){
        System.out.println(this.name+this.salary);
    }





}
