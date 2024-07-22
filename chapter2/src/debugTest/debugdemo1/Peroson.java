package debugTest.debugdemo1;

public class Peroson {


    private String name;
    private int age;

    public Peroson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getInfo(){
        return this.getClass()+this.name+this.age;
    }
}
