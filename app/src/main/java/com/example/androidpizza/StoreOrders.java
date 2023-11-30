package com.example.androidpizza;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;


/**
 * A class that represents the entire order history of the store.
 * Provides options to add orders, remove orders, and holds the next order available.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class StoreOrders {
    private ArrayList<Order> orderList; //arraylist with every order in the store's history
    private static int nextOrder; //next available order
    private static final double njTax = 0.0625; //Sales tax offset

    /**
     * Default constructor for the Store orders.
     */
    public StoreOrders () {
        this.orderList = new ArrayList<Order>();
        this.nextOrder = 1;
    }

    /**
     * Adds an order to the order list.
     * @param o order to add.
     */
    public void addOrder (Order o) {
        orderList.add(o);
    }

    /**
     * removes order from the order list.
     * @param index index of the order to remove.
     */
    public void removeOrder (int index) {this.orderList.remove(index);
    }

    /**
     * Increments and gets the next available order.
     * @return the number of the next available order.
     */
    public int getNextOrder() {
        this.nextOrder = this.orderList.size();
        return this.nextOrder;
    }

    /**
     * Gets the number of total orders the store has had.
     * @return size of the order list array list.
     */
    public int getNumOrders () {
        return this.orderList.size();
    }

    /**
     * Gets an order from a given index.
     * @param index index of the desired order.
     * @return the order.
     */
    public Order getOrder (int index) {
        return this.orderList.get(index);
    }

    /**
     * Writes the arraylist to a text file in order to export store orders.
     * @param fileWriter fileWriter from the controller.
     * @param file file to write to.
     * @throws IOException throws exception if invalid input or output.
     */
    public void export(FileWriter fileWriter, File file) throws IOException {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getSize() != 0) {
                fileWriter.write("Order Number: " + String.valueOf(i+1) + "\n");
                ArrayList<String> orders = orderList.get(i).printList();
                for (int j = 0; j < orders.size(); j++) {
                    fileWriter.write(orders.get(j) + "\n");
                }
                Double price = orderList.get(i).orderPrice();
                String value = String.format("%.2f", price + (price * njTax));
                fileWriter.write("Total Price: " + value + "\n");
            }
        }
    }
}
