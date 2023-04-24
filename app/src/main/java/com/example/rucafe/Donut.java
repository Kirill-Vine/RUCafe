package com.example.rucafe;

/**
 * Class for donut, subclass of MenuItem and parent class for 3 types of donuts
 * @author Kirill Vine
 * @author Michael Burton
 */
public abstract class Donut extends MenuItem {
    String flavor;
    static String[] potentialFlavors = {
            "Vanilla",
            "Chocolate",
            "Strawberry",
            "Banana",
            "Mint",
            "Caramel",
    };

    /**
     * sets flavor if its a possible flavor
     * @param s string that represents flavor for donut
     */
    public void setFlavor(String s) {
        for(int i = 0; i < potentialFlavors.length;i++) {
            if(s.equals(potentialFlavors[i])) {
                flavor = potentialFlavors[i];
                break;
            }
        }
    }

    /**
     *  returns full flavors list
     * @return potentialFlavors list
     */
    public static String[] getFlavors() {
        return potentialFlavors;
    }

    /**
     * returns donut's flavor
     * @return string that represents donut's flavor
     */
    public String getFlavor() {
        return flavor;
    }
}