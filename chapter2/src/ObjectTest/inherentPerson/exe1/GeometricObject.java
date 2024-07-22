package ObjectTest.inherentPerson.exe1;

/**
 * 几何图形类
 */
public class GeometricObject {
    String color;
    double weight;

    public GeometricObject() {
    }

    public GeometricObject(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 计算面积
     * @return 面积
     */

    public double findArea(){
        System.out.println("请指定具体的几何行");
        return -0;
    }
}
