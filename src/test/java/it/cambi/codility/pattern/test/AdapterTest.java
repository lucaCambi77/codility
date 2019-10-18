/**
 * 
 */
package it.cambi.codility.pattern.test;

import org.junit.jupiter.api.Test;

import it.cambi.codility.pattern.Drawing;
import it.cambi.codility.pattern.Rectangle;
import it.cambi.codility.pattern.adapter.RhombusAdapter;
import it.cambi.codility.pattern.adapter.TriangleAdapter;

/**
 * @author luca
 *
 */
public class AdapterTest
{

    @Test
    public void shapeAdapterTest()
    {

        System.out.println("Creating drawing of shapes...");

        Drawing drawing = new Drawing();

        drawing.addShape(new Rectangle());

        drawing.addShape(new TriangleAdapter());

        drawing.addShape(new RhombusAdapter());

        System.out.println("Drawing...");

        drawing.draw();

        System.out.println("Resizing...");

        drawing.resize();
    }
}
