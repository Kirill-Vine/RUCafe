package com.example.rucafe;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Class for coffee, subclass of MenuItem
 * @author Kirill Vine
 * @author Michael Burton
 */
enum sizes {
    SHORT,TALL,GRANDE,VENTI;
}


public class Coffee extends MenuItem{
    public static double ORIGINAL_PRICE = 1.89;
    public static double SIZE_INTERVAL = .40;
    public static double ADDON_COST = .3;
    sizes size;
    double price = ORIGINAL_PRICE;

    ArrayList<String> currentAddons = new ArrayList<String>();
    Coffee(sizes s) {
        size = s;
    }
    String[] addons = {
            "Sweet Cream",
            "French Vanilla",
            "Irish Cream",
            "Caramel",
            "Mocha"
    };


    /**
     * Method for the button that allows the user to add addons to the coffee when pressed
     * @param newAddon name of the addon
     */
    void addAddon(String newAddon) {
        for(int i = 0; i < addons.length; i++) {
            if(newAddon.equals(addons[i]) && !currentAddons.contains(newAddon)) {
                currentAddons.add(newAddon);
            }
        }
    }

    /**
     * Method for the button that allows the user to remove addons from the coffee when pressed
     * @param newAddon name of the addon
     */
    void removeAddon(String newAddon) {
        currentAddons.remove(newAddon);
        currentAddons.trimToSize();
    }

    /**
     * Displays options for sizes of the coffee cups
     * @return size
     */
    sizes getSize() {
        return size;
    }

    /**
     * displays price of the item in the label box
     * @return item price
     */
    @Override
    public double itemPrice() {
        price = ORIGINAL_PRICE;
        int i = 0;
        for(sizes temp: sizes.values()) {
            if(temp.equals(size)) {
                break;
            } else {
                i++;
            }
        }
        price += i*SIZE_INTERVAL;
        price += currentAddons.size()*ADDON_COST;
        return price;
    }

    /**
     * converts to string
     * @return string
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String output = "x" + quantity + ": ";
        switch(size) {
            case SHORT:
                output += "Short";
                break;
            case TALL:
                output += "Tall";
                break;
            case GRANDE:
                output += "Grande";
                break;
            case VENTI:
                output += "Venti";
                break;
        }
        output += " Coffee";
        for(String s: currentAddons) {
            output += " "+ s;
        }
        output += " $" + df.format((double)quantity*itemPrice());
        return output;

    }

}