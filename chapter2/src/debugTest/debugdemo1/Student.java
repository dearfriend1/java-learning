package debugTest.debugdemo1;

public class Student extends Peroson{

    @Override
    public String getInfo() {
        return this.getClass()+":"+getName()+getAge();
    }
}
