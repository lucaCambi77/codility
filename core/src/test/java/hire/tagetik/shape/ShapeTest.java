package hire.tagetik.shape;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class ShapeTest {

  @Test
  void testRectangleShapeArea() {
    Shape shape = new Rectangle(10.0, 10.0);
    assertEquals(100, shape.getArea());
  }

  @Test
  void testCircleShapeArea() {
    Shape shape = new Circle(1.0);
    assertEquals(Math.PI, shape.getArea());
  }

  @Test
  void testGetAreaSumsByType() {

    List<Shape> shapes = new ArrayList<>();
    Rectangle rectangle = new Rectangle(10.0, 10.0);
    Rectangle rectangle2 = new Rectangle(2.0, 2.0);

    shapes.add(rectangle);
    shapes.add(rectangle2);

    Circle circle = new Circle(1.0);
    Circle circle2 = new Circle(2.0);

    shapes.add(circle);
    shapes.add(circle2);

    assertEquals(
        Map.ofEntries(
            Map.entry("CIRCLE", circle.getArea() + circle2.getArea()),
            Map.entry("RECTANGLE", rectangle.getArea() + rectangle2.getArea())),
        getAreaSumsByType(shapes));
  }

  @Test
  void testGetShapesOrderedByAreaGroupedByType() {
    List<Shape> shapes = new ArrayList<>();
    Rectangle rectangle = new Rectangle(10.0, 10.0);
    Rectangle rectangle2 = new Rectangle(2.0, 2.0);

    shapes.add(rectangle);
    shapes.add(rectangle2);

    Circle circle = new Circle(1.0);
    Circle circle2 = new Circle(2.0);

    shapes.add(circle);
    shapes.add(circle2);

    Map<String, List<ShapeByArea>> shapesMap = getShapesOrderedByAreaGroupedByType(shapes);

    assertEquals(2, shapesMap.size());
    assertEquals(2, shapesMap.get("RECTANGLE").size());
    assertEquals(
        shapesMap.get("RECTANGLE"),
        List.of(new ShapeByArea("RECTANGLE", 4.0), new ShapeByArea("RECTANGLE", 100.0)));

    assertEquals(
        shapesMap.get("CIRCLE"),
        List.of(
            new ShapeByArea("CIRCLE", circle.getArea()),
            new ShapeByArea("CIRCLE", circle2.getArea())));
  }

  Map<String, Double> getAreaSumsByType(List<Shape> shapes) {

    Map<String, Double> map = new HashMap<>();

    for (Shape shape : shapes) {
      map.put(shape.getType(), map.getOrDefault(shape.getType(), 0.0) + shape.getArea());
    }

    return new TreeMap<>(map);
  }

  Map<String, List<ShapeByArea>> getShapesOrderedByAreaGroupedByType(List<Shape> shapes) {

    Map<String, List<ShapeByArea>> map = new HashMap<>();

    for (Shape shape : shapes) {
      List<ShapeByArea> shapeByAreas = map.getOrDefault(shape.getType(), new ArrayList<>());
      shapeByAreas.add(new ShapeByArea(shape.getType(), shape.getArea()));
      map.put(shape.getType(), shapeByAreas);
    }

    for (Map.Entry<String, List<ShapeByArea>> entry : map.entrySet()) {
      List<ShapeByArea> shapeByAreas = entry.getValue();
      shapeByAreas.sort(Comparator.comparing(ShapeByArea::getArea));
    }

    return map;
  }
}
