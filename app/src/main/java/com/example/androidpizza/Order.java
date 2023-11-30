package com.example.androidpizza;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class that represents an order.
 * Can contain as many pizzas as needed and will also calculate price of entire order.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class Order {
    private int orderNumber; // order number to identify order.
    private ArrayList<Pizza> orderItems; // arraylist with all pizzas in the order.

    /**
     * Default constructor for the order.
     * First order is order 1.
     */
    public Order () {
        this.orderNumber = 1;
        this.orderItems = new ArrayList<Pizza>();
    }

    /**
     * Returns list of the pizzas in the order.
     * @return arraylist of all pizzas in order.
     */
    public ArrayList<Pizza> getOrderItems () {
        return this.orderItems;
    }

    /**
     * Adds given pizza to the orderItems arraylist.
     * @param p pizza to add.
     */
    public void addToOrder (Pizza p) {
        int count = 0;
        for (Pizza item : orderItems) {
            count++;
        }
        orderItems.add(count,p);
    }

    /**
     * Increases order number.
     */
    public void incrementOrder () {
        this.orderNumber++;
    }

    /**
     * Returns every pizza in the order in String format.
     * @return String representation of every pizza.
     */
    public ArrayList<String> printList () {
        ArrayList<String> view = new ArrayList<String>();
        for (int i = 0; i < orderItems.size(); i++) {
            view.add(this.orderItems.get(i).toString());
        }
        return view;
    }

    /**
     * Removes the given pizza at index from order.
     * @param index index of pizza to remove.
     */
    public void removeItem (int index) {
        orderItems.remove(index);
    }

    /**
     * Getter method for order size.
     * @return size of the orderItems arraylist.
     */
    public int getSize () {
        return orderItems.size();
    }

    /**
     * Clears every item in the order for when the order needs to be cancelled.
     */
    public void clearOrder () {
        this.orderItems = new ArrayList<Pizza>();
    }

    /**
     * Calculates combined price of every item in the order.
     * @return double containing total order price.
     */
    public double orderPrice () {
        double price = 0;
        for (int i = 0; i < orderItems.size(); i++) {
                price += orderItems.get(i).price();
            }
        return price;
        }

}
