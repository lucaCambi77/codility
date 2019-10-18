/**
 * 
 */
package it.cambi.codility.pattern.adapter;

import it.cambi.codility.pattern.Shape;

/**
 * @author luca
 *
 */
public class RhombusAdapter extends GeometricShapeAdapter<Rhombus> implements Shape
{

    public RhombusAdapter()
    {

        super(new Rhombus());
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
    public String description()
    {

        return "Rhombus object";

    }

    @Override
    public boolean isHide()
    {

        return false;

    }

}
