package com.example.rucafe;
import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 * Class for orders list
 * @author Kirill Vine
 * @author Michael Burton
 */
public class Order {
    static int currentOrderNumber = 0;
    int orderNumber;
    double orderPrice = 0;
    ArrayList<MenuItem> items = new ArrayList<>();
    Order() {
        orderNumber = currentOrderNumber;
        currentOrderNumber++;
    }

    /**
     * Adds item to order
     * @param item
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }
    /**
     * Removes item to order
     * @param item
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
        items.trimToSize();
    }

    /**
     * Displays price of the order
     * @return order price
     */
    public double getOrderPrice() {
        double finalPrice = 0;
        for(MenuItem item: items) {
            finalPrice += item.itemPrice()* item.getQuantity();
        }
        return finalPrice;
    }

    /**
     * @return items in the array of items
     */
    public ArrayList<MenuItem> getItems(){
        return items;
    }
    /**
     * convert order data to string
     * @return string
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String output = "Order #" + (orderNumber+1) + ": \n";
        for(MenuItem item : items) {
            output+= "\t" + item.toString() + "\n";
        }
        return output;
    }
}