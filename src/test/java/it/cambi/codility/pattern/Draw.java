/**
 * 
 */
package it.cambi.codility.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luca
 *
 */
public class Draw
{

    List<Shape> shapes = new ArrayList<Shape>();

    public Draw()
    {

        super();

    }

    public void addShape(Shape shape)
    {

        shapes.add(shape);

    }

    public List<Shape> getShapes()
    {

        return new ArrayList<Shape>(shapes);

    }

    public void draw()
    {

        if (shapes.isEmpty())
        {

            System.out.println("Nothing to draw!");

        }
        else
        {

            shapes.stream().forEach(shape -> shape.draw());

        }

    }

    public void resize()
    {

        if (shapes.isEmpty())
        {

            System.out.println("Nothing to resize!");

        }
        else
        {

            shapes.stream().forEach(shape -> shape.resize());

        }

    }

}
