package com.zedrick.multi.Grocery.Classes;

import java.util.ArrayList;

public class Cart {

    ArrayList<Item> items;
    public double total;
    public int totalItems;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
        totalItems = 0;
    }

    public void addItem(Item item) {
    }

    public void removeItem(Item item) {
    }

    public void listItems() {
    }
}