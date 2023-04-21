package com.example.rucafe;

import java.text.DecimalFormat;
/**
 * Class for donut hole, subclass of Donut
 * @author Kirill Vine
 * @author Michael Burton
 */
public class DonutHole extends Donut{
    public final static double price = 0.39;
    public DonutHole() {
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
     * compares current object with a donut hole flavor
     * @param o object being compared
     * @return whether the object is a donut hole
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DonutHole) {
            DonutHole hole = (DonutHole) o;
            if (hole.flavor.equals(this.flavor)) {
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
        return "x" + quantity + ": " + flavor + " DonutHole: " + df.format((double)quantity*itemPrice());
    }
}
