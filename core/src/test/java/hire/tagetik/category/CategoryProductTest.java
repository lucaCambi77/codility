package hire.tagetik.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class CategoryProductTest {

  @Test
  void testCategory() {
    Category category = new Category("name", 1);
    assertEquals("name", category.name());
    assertEquals(1, category.order());
  }

  @Test
  void testProduct() {
    Product product = new Product("product", new Category("name", 1), 10, 5.0);
    assertEquals("product", product.product());
    assertEquals(10, product.quantity());
    assertEquals(5.0, product.price());
    assertEquals(new Category("name", 1), product.category());
  }

  @Test
  void testTotalAmountByCategory() {
    Category cat1 = new Category("cat1", 1);
    Category cat2 = new Category("cat2", 2);

    Product product = new Product("P1", cat1, 10, 5.0);
    Product product2 = new Product("P2", cat1, 1, 5.0);
    Product product3 = new Product("P3", cat2, 1, 5.0);

    assertEquals(
        Map.ofEntries(Map.entry(cat1, 55.0), Map.entry(cat2, 5.0)),
        getTotalAmountByCategory(List.of(product3, product, product2)));
  }

  @Test
  void testCategoryOrderedByAmountGroupedByType() {
    Category cat1 = new Category("cat1", 1);
    Category cat2 = new Category("cat2", 2);

    Product product = new Product("P1", cat1, 10, 5.0);
    Product product2 = new Product("P2", cat1, 1, 5.0);
    Product product3 = new Product("P3", cat2, 1, 5.0);

    assertEquals(
        Map.ofEntries(
            Map.entry(cat1, List.of(product2, product)), Map.entry(cat2, List.of(product3))),
        getCategoryOrderedByAmountGroupedByType(List.of(product3, product, product2)));
  }

  Map<Category, Double> getTotalAmountByCategory(List<Product> productList) {
    TreeMap<Category, Double> totalAmountByCategory =
        new TreeMap<>(Comparator.comparing(Category::order));

    for (Product product : productList) {
      Category category = product.category();
      totalAmountByCategory.put(
          category,
          totalAmountByCategory.getOrDefault(category, 0.0) + product.price() * product.quantity());
    }

    return totalAmountByCategory;
  }

  Map<Category, List<Product>> getCategoryOrderedByAmountGroupedByType(List<Product> productList) {

    Map<Category, List<Product>> categoryOrderedByType =
        new TreeMap<>(Comparator.comparing(Category::order));

    for (Product product : productList) {
      List<Product> products =
          categoryOrderedByType.getOrDefault(product.category(), new ArrayList<>());
      products.add(product);
      categoryOrderedByType.put(product.category(), products);
    }

    for (Map.Entry<Category, List<Product>> entry : categoryOrderedByType.entrySet()) {
      List<Product> products = entry.getValue();
      products.sort(Comparator.comparing(Product::quantity));
    }

    return categoryOrderedByType;
  }
}
