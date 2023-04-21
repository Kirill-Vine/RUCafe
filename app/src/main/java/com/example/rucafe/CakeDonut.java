package com.example.rucafe;

import java.text.DecimalFormat;

/**
 * Class for cake donut, subclass of Donut
 * @author Kirill Vine
 * @author Michael Burton
 */
public class CakeDonut extends Donut{
    public final static double price = 1.79;
    public CakeDonut() {
        String[] tempFlavors = {
                "Chocolate",
                "Strawberry",
                "Vanilla",
                "Caramel",
                "Banana",
                "Mint",
        };
        super.flavor = potentialFlavors[0];
        super.potentialFlavors = tempFlavors;
    }

    /**
     * displays price of the donut
     * @return price of the donut
     */
    @Override
    public double itemPrice() {
        return price;
    }

    /**
     * compares current object with a cake donut flavor
     * @param o object being compared
     * @return whether the object is a cake donut
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof CakeDonut) {
            CakeDonut cake = (CakeDonut)o;
            if(cake.flavor.equals(this.flavor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * convert donut data to string
     * @return string
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return "x" + quantity + ": " + flavor + " Cake Donut: " + df.format((double)quantity*itemPrice());
    }
}
