package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 */
public class RhombusAdapter extends Rhombus implements Shape {

  public RhombusAdapter() {
    super();
  }

  public RhombusAdapter(double a, double b) {
    super(a, b);
  }

  @Override
  public void draw() {

    super.drawShape();
  }

  @Override
  public void resize() {
    System.out.println("Rhombus can't be resized. Please create new one with required values.");
  }

  @Override
  public String getDescription() {

    return "Rhombus object";
  }

  @Override
  public boolean isHide() {

    return false;
  }
}
