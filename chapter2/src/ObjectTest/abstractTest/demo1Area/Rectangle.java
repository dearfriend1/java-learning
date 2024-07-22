package ObjectTest.abstractTest.demo1Area;

public class Rectangle extends GeometricObject {

    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
    }

    @Override
    public double findArea() {
        return width*height;
    }
}
