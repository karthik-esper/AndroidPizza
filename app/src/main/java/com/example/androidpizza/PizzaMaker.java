package com.example.androidpizza;

/**
 * Factory class that creates pizzas for you based on the given type.
 * @author Karthik Gangireddy, Vineal Sunkara.
 */
public class PizzaMaker {
    /**
     * creates pizza based on the given input String.
     * @param pizzaType string representation of pizza type.
     * @return pizza of selected type.
     */
    public static Pizza createPizza(String pizzaType) {
        switch (pizzaType.toLowerCase()) {
            case "deluxe":
                return new Deluxe();
            case "supreme":
                return new Supreme();
            case "meatzza":
                return new Meatzza();
            case "seafood":
                return new Seafood();
            case "pepperoni":
                return new Pepperoni();
            case "byop":
                return new BuildYourOwnPizza();
            case "spicy" :
                return new Spicy();
            case "hawaiian":
                return new Hawaiian();
            case "sussy":
                return new Sussy();
            case "veggie":
                return new Veggie();
            case "surfturf":
                return new SurfTurf();
            default:
                return null;
        }
    }
}
