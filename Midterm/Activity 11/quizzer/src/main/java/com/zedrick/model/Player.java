package com.zedrick.model;
public class Player {
    private final String name;
    private String password;
    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    public String getName() {
        return name;
    }
}
