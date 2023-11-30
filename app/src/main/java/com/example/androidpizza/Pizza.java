package com.example.androidpizza;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Abstract class used as the base class for any pizza type.
 * Shows the characteristics of the pizza using different enum classes.
 * @authors Karthik Gangireddy, Vineal Sunkara
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price(); //polymorphism
    public abstract void setSize(Size size); //setter method
    public abstract void setExtraSauce(boolean extraSauce); //setter method
    public abstract void setExtraCheese(boolean extraCheese); //setter method
    public boolean getExtraSauce() {return this.extraSauce;} //getter method
    public boolean getExtraCheese() {return this.extraCheese;} //getter method
    public abstract String toString(); //polymorphism
    public abstract ArrayList<Topping> getToppings();
    public abstract void setToppings(ArrayList<Topping> toppings);

}
