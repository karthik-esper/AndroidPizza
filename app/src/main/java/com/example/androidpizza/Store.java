package com.example.androidpizza;

/**
 * Singleton class used to share data between controllers.
 * Contains an instance of the Order and StoreOrders to allow data transfer.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class Store {
    private static Store instance = new Store(); // Singleton instance.
    private Order currentOrder; // Order that can have pizzas added or removed.
    private StoreOrders orderHistory; // Order history of entire store.

    /**
     * default constructor withe empty order and storeorders to start.
     */
    private Store () {
        currentOrder = new Order();
        orderHistory = new StoreOrders();
    }

    /**
     * gets the instance of the store.
     * @return instance of Store;
     */
    public static synchronized Store getInstance() {
        if (instance != null) {
            instance = new Store();
        }
        return instance;
    }

    /**
     * gets the currentOrder being added to.
     * @return current order.
     */
    public Order getCurrentOrder () {
        return currentOrder;
    }

    /**
     * Sets the next order to be added to the order history.
     */
    public void setNextOrder() {
        Order placeholder = new Order();
        placeholder.incrementOrder();
        this.orderHistory.addOrder(placeholder);
        this.currentOrder = placeholder;
    }

    /**
     * Returns the full history of orders.
     * @return the StoreOrders of the current store.
     */
    public StoreOrders getOrderHistory() {
        return orderHistory;
    }
}
