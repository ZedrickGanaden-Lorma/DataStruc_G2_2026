package com.zedrick.multi.Grocery.Classes;

public class Product {

    public Product() {
    }

    public Product(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    String name;
    double price;
    String type;
}
