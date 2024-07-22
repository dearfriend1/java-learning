package ObjectTest.inherentPerson.exe1;

public class Test {

    public static boolean equalsArea(GeometricObject g1, GeometricObject g2){
        return g1.findArea() == g2.findArea();
    }

    public static void displayGeometricObject(GeometricObject g){
        System.out.println(g.findArea());
    }


    public static void main(String[] args){
        GeometricObject c1 = new Circle("red",12,1);
        GeometricObject t1 = new MyRectangle("red",12,1,1);

        System.out.println(equalsArea(c1,t1));

        displayGeometricObject(c1);
        displayGeometricObject(t1);




    }
}
