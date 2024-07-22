package ObjectTest.inherentPerson.exe1;

public class Circle extends GeometricObject {

    double radius;

    public Circle() {
    }

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double findArea() {
        return this.radius*this.radius*Math.PI;
    }
}
