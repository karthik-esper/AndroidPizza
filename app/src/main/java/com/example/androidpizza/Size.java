package com.example.androidpizza;

/**
 * Enum class for the different sizes, small, medium, and large.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public enum Size {
    S("Small"),
    M("Medium"),
    L("Large");

    private final String pizzaSize; //String value of the enum

    /**
     * Constructor for the enum class.
     * @param pizzaSize size of the pizza.
     */
    private Size (String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    /**
     * Getter method for pizza size.
     * @return size of pizza.
     */
    public String getPizzaSize () {
        return this.pizzaSize;
    }
}
