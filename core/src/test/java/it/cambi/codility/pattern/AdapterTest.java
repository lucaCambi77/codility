/**
 *
 */
package it.cambi.codility.pattern;

import it.cambi.codility.pattern.adapter.RhombusAdapter;
import it.cambi.codility.pattern.adapter.TriangleAdapter;
import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class AdapterTest {

    @Test
    public void shapeAdapterTest() {

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
