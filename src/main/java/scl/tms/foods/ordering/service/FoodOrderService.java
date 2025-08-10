```java
package scl.tms.foods.ordering.service;

import scl.tms.foods.ordering.domain.DeliveryDetails;
import scl.tms.foods.ordering.repository.OrderRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodOrderService {

    private static final Logger LOGGER = Logger.getLogger(FoodOrderService.class.getName());
    private final Map<String, List<String>> orders = new HashMap<>();
    private final OrderRepository orderRepository;

    public FoodOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void initiateOrder(String customerId) {
        orders.put(customerId, new ArrayList<>());
        LOGGER.log(Level.INFO, "Order initiated for customer: {0}", customerId);
    }

    public void addItemToOrder(String customerId, String itemId) {
        if (!orders.containsKey(customerId)) {
            initiateOrder(customerId);
        }
        orders.get(customerId).add(itemId);
        LOGGER.log(Level.INFO, "Item {0} added to order for customer: {1}", new Object[]{itemId, customerId});
    }

    public void setDeliveryDetails(String customerId, DeliveryDetails deliveryDetails) {
        // Persist delivery details using the repository.  Replace with actual persistence logic.
        try {
            orderRepository.saveDeliveryDetails(customerId, deliveryDetails);
            LOGGER.log(Level.INFO, "Delivery details set for customer: {0}", customerId);
        } catch (Exception e) {
             LOGGER.log(Level.SEVERE, "Error saving delivery details for customer " + customerId, e);
        }

    }

    public void cancelOrder(String orderId) {
        try {
            orderRepository.cancelOrder(orderId);
             LOGGER.log(Level.INFO, "Order cancelled with ID: {0}", orderId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error cancelling order " + orderId, e);
        }
    }

    public String getOrderSummary(String orderId) {
       try {
            String summary = orderRepository.getOrderSummary(orderId);
            LOGGER.log(Level.INFO, "Order summary retrieved for order ID: {0}", orderId);
            return summary;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting order summary for order " + orderId, e);
            return "Error retrieving order summary."; // Or throw an exception.  Handle appropriately for your application.
        }
    }
}
```