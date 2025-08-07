
package scl.tms.foods.ordering.model;

import java.util.*;

public class Order {
    private String orderId;
    private String customerId;
    private List<String> items;
    private Date deliveryDate;

    public Order(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public void addItem(String itemId) {
        items.add(itemId);
    }

    public List<String> getItems() {
        return items;
    }
}
