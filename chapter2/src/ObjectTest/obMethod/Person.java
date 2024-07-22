package ObjectTest.obMethod;

public class Person{
    String name;

    int age;


    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        //快速判断（是否是同一个地址）
        if (this == obj){
            return true;
        }
        //判断传入对象是否是同一个类型
        if(obj instanceof Person){
            Person anotherObj = (Person) obj;
            return this.age == anotherObj.age && this.name.equals(anotherObj.name);
        }
        return false;
    }

    public String toString(){
        return this.getClass()+"{Name:"+name+",Age"+age+"}";
    }
}
