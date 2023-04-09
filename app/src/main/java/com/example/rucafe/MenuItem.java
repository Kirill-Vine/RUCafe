package com.example.rucafe;

/**
 * Class for MenuItem, parent class for Donut and Coffee classes
 * @author Kirill Vine
 * @author Michael Burton
 */
public abstract class MenuItem {
    int quantity = 1;
    public abstract double itemPrice();

    /**
     * sets quantity of items
     * @param i quantity
     */
    public void setQuantity(int i) {
        quantity = i;
    }

    /**
     * Displays quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
}