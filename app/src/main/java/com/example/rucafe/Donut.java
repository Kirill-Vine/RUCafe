package com.example.rucafe;

/**
 * Class for donut, subclass of MenuItem and parent class for 3 types of donuts
 * @author Kirill Vine
 * @author Michael Burton
 */
public abstract class Donut extends MenuItem {
    String flavor;
    static String[] potentialFlavors = {
            "Chocolate",
            "Strawberry",
            "Vanilla",
            "Caramel",
            "Banana",
            "Mint"
    };

    /**
     * sets flavor if its a possible flavor
     */
    public void setFlavor(String s) {
        for(int i = 0; i < potentialFlavors.length;i++) {
            if(s.equals(potentialFlavors[i])) {
                flavor = potentialFlavors[i];
                break;
            }
        }
    }
    public static String[] getFlavors() {
        return potentialFlavors;
    }
}