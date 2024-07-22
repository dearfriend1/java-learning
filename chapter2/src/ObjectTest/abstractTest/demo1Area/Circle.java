package ObjectTest.abstractTest.demo1Area;

public class Circle extends GeometricObject{

    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double findArea() {
        return radius*radius*Math.PI;
    }
}
