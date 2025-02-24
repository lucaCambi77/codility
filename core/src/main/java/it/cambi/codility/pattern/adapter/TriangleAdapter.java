package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 */
public class TriangleAdapter extends Triangle implements Shape {

  public TriangleAdapter() {
    super();
  }

  public TriangleAdapter(double a, double b, double c) {

    super(a, b, c);
  }

  @Override
  public void draw() {

    super.drawShape();
  }

  @Override
  public void resize() {
    System.out.println("Triangle can't be resized. Please create new one with required values.");
  }

  @Override
  public String getDescription() {

    return "Triangle object";
  }

  @Override
  public boolean isHide() {

    return false;
  }
}
