package hire.tagetik.shape;

import java.util.Objects;

class ShapeByArea {
  String shape;
  double area;

  ShapeByArea(String shape, double area) {
    this.shape = shape;
    this.area = area;
  }

  public String getShape() {
    return shape;
  }

  public double getArea() {
    return area;
  }

  @Override
  public String toString() {
    return "ShapeByArea [shape=" + shape + ", area=" + area + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ShapeByArea other = (ShapeByArea) obj;
    return Double.compare(area, other.area) == 0 && Objects.equals(shape, other.shape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shape, area);
  }
}
