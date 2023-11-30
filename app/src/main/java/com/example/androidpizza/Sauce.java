package com.example.androidpizza;

/**
 * Enum class for the different sauces you can pick between, alfredo vs tomato.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public enum Sauce {
    AL("ALFREDO"),
    TO("TOMATO");

    private final String flavor; // String that holds the pizza flavor

    /**
     * Constructor for the pizza's sauce.
     * @param flavor flavor of the sauce.
     */
    private Sauce (String flavor) {
        this.flavor = flavor;
    }

    /**
     * Getter method for the flavor of the sauce.
     * @return flavor of the sauce.
     */
    public String getFlavor() {
        return this.flavor;
    }

}
