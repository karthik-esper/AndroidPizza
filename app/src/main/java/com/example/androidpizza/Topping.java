package com.example.androidpizza;

/**
 * Enum class for the different pizza toppings you can have.
 * Contains both possible meats and veggies.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public enum Topping {
    SAU("Sausage"),
    PE("Pepperoni"),
    BE("Beef"),
    HA("Ham"),
    SH("Shrimp"),
    SQ("Squid"),
    CM("Crab Meat"),
    GP("Green Pepper"),
    ON("Onion"),
    MU("Mushroom"),
    BO("Black Olive"),
    PI("Pineapple"),
    JA("Jalapeno");

    private final String toppingName; // name of topping

    /**
     * Constructor for the enum class.
     * @param toppingName name of the topping.
     */
    private Topping (String toppingName) {
        this.toppingName = toppingName;
    }

    /**
     * Getter method for the topping name.
     * @return name of the topping.
     */
    public String getToppingName () {
        return this.toppingName;
    }

    @Override
    public String toString() {
        return this.getToppingName();
    }
}
