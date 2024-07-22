package ObjectTest.inherentPerson;

public class Man extends Person{

    String gender = "man";
    String name;




    public Man(String name, int age, double height, double weight, String gender) {
        super(name, age, height, weight);
        this.gender = gender;
    }

    public Man(String name) {
       this.name = name;
    }

//    public Man(String gender) {
//
//        this.gender = gender;
//    }

    public void shortHair(){
        Sleep();
        System.out.println("Shorthair Man Method!");
    }

    
}
