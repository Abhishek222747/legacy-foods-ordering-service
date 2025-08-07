
package scl.tms.foods.ordering.service;

import java.util.*;

public class FoodOrderService {
    private Map<String, List<String>> orders = new HashMap<>();

    public void initiateOrder(String customerId) {
        orders.put(customerId, new ArrayList<>());
    }

    public void addItemToOrder(String customerId, String itemId) {
        if (!orders.containsKey(customerId)) {
            initiateOrder(customerId);
        }
        orders.get(customerId).add(itemId);
    }

    public void setDeliveryDetails(String customerId, Date deliveryDate, String address, int priority, boolean giftWrap, String notes, String deliverySlot) {
        System.out.println("Delivery details set");
    }

    public void cancelOrder(String orderId) {
        System.out.println("Cancelled order with ID: " + orderId);
    }

    public String getOrderSummary(String orderId) {
        return "Summary for Order ID: " + orderId;
    }
}
