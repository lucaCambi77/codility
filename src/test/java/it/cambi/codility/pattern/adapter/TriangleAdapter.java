/**
 * 
 */
package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 *
 */
public class TriangleAdapter extends GeometricShapeAdapter<Triangle> implements Shape
{

    public TriangleAdapter()
    {

        super(new Triangle());
    }

    @Override
    public void draw()
    {

        getAdaptee().drawShape();

    }

    @Override
    public void resize()
    {

        super.resize();
    }

    @Override
    public String getDescription()
    {

        return "Triangle object";

    }

    @Override
    public boolean isHide()
    {

        return false;

    }

}
