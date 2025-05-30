package hire.tagetik.sellbuy;

import java.util.List;

public record TradeResult(double avgPrice, double quantity, List<Order> updatedSellOrders) {}
