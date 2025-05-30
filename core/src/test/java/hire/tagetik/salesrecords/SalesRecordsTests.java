package hire.tagetik.salesrecords;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class SalesRecordsTests {

  @Test
  void test() {

    Category productA = new Category("Product", "A");
    Category productB = new Category("Product", "B");
    Category north = new Category("Region", "North");
    Category south = new Category("Region", "South");

    SalesRecord salesRecord = new SalesRecord(1, List.of(productA, north), 100);
    SalesRecord salesRecord1 = new SalesRecord(2, List.of(productB, south), 150);
    SalesRecord salesRecord2 = new SalesRecord(3, List.of(productA, north), 200);

    assertEquals(
        Map.of(productA, 300.0, productB, 150.0),
        aggregate(List.of(salesRecord, salesRecord1, salesRecord2), "Product", "sum"));
  }

  Map<Category, Double> aggregate(
      List<SalesRecord> salesRecordList, String category, String aggregation) {
    Map<Category, Double> map = new TreeMap<>(Comparator.comparing(Category::value));

    switch (aggregation) {
      case "sum":
        {
          for (SalesRecord salesRecord : salesRecordList) {
            List<Category> categories = salesRecord.category();

            for (Category cat : categories) {
              if (category.equals(cat.type())) {
                map.put(cat, map.getOrDefault(cat, 0.0) + salesRecord.value());
              }
            }
          }
        }
    }
    return map;
  }
}
