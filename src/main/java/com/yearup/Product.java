package com.yearup;



public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(id + " - " + name + " - $" + price);
    }
}

