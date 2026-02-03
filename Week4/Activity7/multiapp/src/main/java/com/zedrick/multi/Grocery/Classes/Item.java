package com.zedrick.multi.Grocery.Classes;

public class Item {

    public Item(Product product) {
    }

    public Item(Product product, int quantity) {
        this.name = product.name;
        this.quantity = quantity;
        this.price = product.price;
        this.total = product.price * quantity;
    }

    String name;
    int quantity;
    double price, total;
}