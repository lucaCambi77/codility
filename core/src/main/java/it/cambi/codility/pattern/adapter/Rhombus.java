/** */
package it.cambi.codility.pattern.adapter;

/**
 * @author luca
 */
// Part of Extra-Geometric-Shape API
public class Rhombus implements AbstractGeometricShape, GeometricShape {

  // sides
  private final double a;

  private final double b;

  public Rhombus() {

    this(1.0d, 1.0d);
  }

  public Rhombus(double a, double b) {

    this.a = a;

    this.b = b;
  }

  @Override
  public double area() {

    return a * b;
  }

  @Override
  public double perimeter() {

    return 2 * (a + b);
  }

  @Override
  public void drawShape() {

    System.out.println("Drawing Rhombus with area: " + area() + " and perimeter: " + perimeter());
  }
}
