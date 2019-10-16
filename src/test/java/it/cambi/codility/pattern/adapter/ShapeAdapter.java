/**
 * 
 */
package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 *
 */
public class ShapeAdapter<T extends BaseGeometricShape> implements Shape
{

    private T shape;

    public ShapeAdapter(T shape)
    {

        super();
        this.shape = shape;
    }

    @Override
    public void draw()
    {

        shape.drawShape();

    }

    @Override

    public void resize()
    {

        System.out.println("Triangle can't be resized. Please create new one with required values.");

    }

    @Override

    public String description()
    {

        return "Triangle object";

    }

    @Override

    public boolean isHide()
    {

        return false;

    }

}
