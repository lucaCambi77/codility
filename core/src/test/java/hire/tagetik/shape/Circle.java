package hire.tagetik.shape;

public class Circle extends Shape {

  private final double radius;

  public Circle(double radius) {
    super();
    this.radius = radius;
  }

  @Override
  double getArea() {
    return radius * radius * Math.PI;
  }

  @Override
  String getType() {
    return "CIRCLE";
  }
}
