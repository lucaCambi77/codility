/**
 * 
 */
package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 *
 */
public abstract class GeometricShapeAdapter<T extends BaseGeometricShape> implements Shape
{

    private T adaptee;

    public GeometricShapeAdapter(T shape)
    {

        super();
        this.adaptee = shape;
    }

    @Override
    public void draw()
    {

        adaptee.drawShape();

    }

    @Override
    public void resize()
    {

        System.out.println(description() + " can't be resized");
    }

    @Override
    public String description()
    {

        return "Description";

    }

    @Override
    public boolean isHide()
    {

        return false;

    }

    public T getAdaptee()
    {
        return adaptee;
    }

}
