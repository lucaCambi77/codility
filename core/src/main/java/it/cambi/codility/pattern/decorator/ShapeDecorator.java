/** */
package it.cambi.codility.pattern.decorator;

import it.cambi.codility.pattern.Shape;

/** @author luca */
public abstract class ShapeDecorator implements Shape {

  protected Shape decoratedShape;

  public ShapeDecorator(Shape decoratedShape) {

    super();

    this.decoratedShape = decoratedShape;
  }
}
