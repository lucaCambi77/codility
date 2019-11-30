/**
 * 
 */
package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 *
 */
public abstract class GeometricShapeAdapter<T extends AbstractGeometricShape> implements Shape
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

        System.out.println(getDescription() + " can't be resized");
    }

    @Override
    public String getDescription()
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
