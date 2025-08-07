
package scl.tms.foods.ordering.controller;

import java.util.*;
import scl.tms.foods.ordering.service.*;
import scl.tms.foods.ordering.model.*;

public class FoodOrderController {
    private FoodOrderService foodOrderService = new FoodOrderService();
    private InventoryService inventoryService = new InventoryService();
    private ReportService reportService = new ReportService();

    public void processOrder(String customerId, List<String> itemIds, Date deliveryDate, String address, int priority, boolean giftWrap, String notes, String deliverySlot) {
        if (itemIds == null || itemIds.isEmpty()) {
            System.out.println("No items selected");
            return;
        }

        foodOrderService.initiateOrder(customerId);
        for (String itemId : itemIds) {
            inventoryService.checkAndReserveItem(itemId);
            foodOrderService.addItemToOrder(customerId, itemId);
        }
        foodOrderService.setDeliveryDetails(customerId, deliveryDate, address, priority, giftWrap, notes, deliverySlot);
        reportService.generateReport(customerId);
    }

    public void cancelOrder(String orderId) {
        foodOrderService.cancelOrder(orderId);
        System.out.println("Order cancelled");
    }

    public void printOrderSummary(String orderId) {
        String summary = foodOrderService.getOrderSummary(orderId);
        System.out.println(summary);
    }
}
