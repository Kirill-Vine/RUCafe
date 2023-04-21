package com.example.rucafe;

import java.text.DecimalFormat;
/**
 * Class for yeast donut, subclass of Donut
 * @author Kirill Vine
 * @author Michael Burton
 */
public class YeastDonut extends Donut{
    public final static double price = 1.59;
    public YeastDonut() {
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
        if(o instanceof YeastDonut) {
            YeastDonut yeast = (YeastDonut)o;
            if(yeast.flavor.equals(this.flavor)) {
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
        return "x" + quantity + ": " +flavor + " Yeast Donut: $" + df.format((double)quantity*itemPrice());
    }
}
