package hire.tagetik.shape;

public class Rectangle extends Shape {

  private final double width;
  private final double height;

  public Rectangle(double width, double heigth) {
    super();
    this.width = width;
    this.height = heigth;
  }

  @Override
  double getArea() {
    return width * height;
  }

  @Override
  String getType() {
    return "RECTANGLE";
  }
}
