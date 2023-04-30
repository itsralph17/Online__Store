package com.yearup;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<LineItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addLineItem(LineItem lineItem) {
        items.add(lineItem);
    }

    public void display() {
        for (LineItem item : items) {
            System.out.println(item.getProduct().getName() + " - " + item.getQuantity() + " x $" + item.getProduct().getPrice());
        }
    }

    public double getTotal() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
