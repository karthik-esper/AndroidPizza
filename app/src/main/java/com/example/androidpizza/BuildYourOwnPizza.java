package com.example.androidpizza;

import java.util.ArrayList;

/**
 * Subclass of Pizza that represents the BuildYourOwnPizza option
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class BuildYourOwnPizza extends Pizza {
    private static final double BASE_PRICE = 8.99; // Base price of the pizza
    private static final double ADD_MEDIUM = 2.00; // extra charge for medium size
    private static final double ADD_LARGE = 4.00; // extra charge for large size
    private static final double ADD_MORE_TOPPINGS = 1.49; //extra charge for additional toppings past 3
    private static final int TOPPING_START = 3; //starting place in the toppings ArrayList since the first 3 are included in price
    private static final double EXTRA_CHARGE = 1.00; //charge for selecting extra sauce or cheese
    private static final int TOPPING_END = 7; //maximum number of toppings allowed for BYOP

    /**
     * Default Constructor for building your own pizza
     */
    public BuildYourOwnPizza() {
    }

    /**
     * Calculates the price for a specialty pizza based on the size and number of toppings.
     * @return double containing price.
     */
    public double price() {
        double price = BASE_PRICE;
        if (this.size.getPizzaSize().equals("Medium")) {
            price += ADD_MEDIUM;
        } else if (this.size.getPizzaSize().equals("Large")) {
            price += ADD_LARGE;
        }

        if (toppings.size() < TOPPING_START || toppings.size() > TOPPING_END) {
            return 0;
        }
        
        for (int i = TOPPING_START; i < toppings.size(); i++) {
            price += ADD_MORE_TOPPINGS;
        }
        if (getExtraCheese()) {
            price += EXTRA_CHARGE;
        }
        if (getExtraSauce()) {
            price += EXTRA_CHARGE;
        }
        return price;
    }

    /**
     * Setter method to change pizza sauce
     * @param sauce sauce to change the pizza to.
     */
    public void setSauce(Sauce sauce) {this.sauce = sauce;}

    /**
     * Setter method to change pizza size.
     * @param size size of the pizza.
     */
    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Setter method to change whether there is extra sauce or not.
     *
     * @param extraSauce whether there is extra sauce or not.
     */
    @Override
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    /**
     * Setter method to change whether there is extra sauce or not.
     *
     * @param extraCheese whether there is extra cheese or not.
     */
    @Override
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Returns a string representation of the pizza and it's toppings.
     * @return string containing pizza, toppings, size, etc.
     */
    @Override
    public String toString() {
        String pizza = "[Build Your Own Pizza]";
        for (int i = 0; i < this.toppings.size(); i++) {
            pizza += " " + toppings.get(i).toString();
        }
        pizza += " " + this.sauce.getFlavor();
        pizza += " " + this.size.getPizzaSize() + " ";
        if (this.extraCheese) {
            pizza += "extraCheese ";
        }
        if (this.extraSauce) {
            pizza += "extraSauce ";
        }
        pizza += String.format("%.2f", this.price());
        return pizza;
    }

    /**
     * sets toppings to a certain arraylist.
     * @param toppings arraylist to set toppings to.
     */
    @Override
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;

    }

    /**
     * Returns the toppings ArrayList.
     * @return the toppings ArrayList.
     */
    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    };
}
