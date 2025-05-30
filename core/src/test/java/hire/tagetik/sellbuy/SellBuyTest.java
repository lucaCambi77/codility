package hire.tagetik.sellbuy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SellBuyTest {

  @Test
  void orderTest() {
    Order order = new Order(Verb.SELL, 10.0, 5);
    assertEquals(Verb.SELL, order.verb());
    assertEquals(10.0, order.price(), 0);
    assertEquals(5, order.quantity());
  }

  @Test
  void tradeTest() {
    Order buy = new Order(Verb.BUY, 110.0, 30);
    Order sell1 = new Order(Verb.SELL, 100.0, 10);
    Order sell2 = new Order(Verb.SELL, 150, 20);
    Order sell3 = new Order(Verb.SELL, 80.0, 40);

    TradeResult tradeResult = execTrade(buy, List.of(sell1, sell2, sell3));
    assertEquals(30, tradeResult.quantity());
    assertEquals(86.66, tradeResult.avgPrice());
    assertEquals(
        List.of(new Order(Verb.SELL, 150, 20), new Order(Verb.SELL, 80.0, 20)),
        tradeResult.updatedSellOrders());
  }

  private TradeResult execTrade(Order buy, List<Order> sell) {

    double quantity = buy.quantity();
    double totalPrice = 0;
    List<Order> updatedSellOrders = new ArrayList<>();
    int q;

    for (Order order : sell) {
      if (buy.price() >= order.price()) {
        q = (int) Math.min(quantity, order.quantity());

        totalPrice += order.price() * q;
        quantity -= q;

        if (order.quantity() - q > 0) {
          updatedSellOrders.add(new Order(order.verb(), order.price(), order.quantity() - q));
        }
      } else {
        updatedSellOrders.add(order);
      }
    }

    if (quantity != 0) {
      throw new RuntimeException("Trade failed");
    }

    return new TradeResult(
        Math.floor(totalPrice / buy.quantity() * 100) / 100, buy.quantity(), updatedSellOrders);
  }
}
