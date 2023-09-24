package com.example.tixid;

public class Theatre {
    private String name;
    private String description;
    private double x;
    private double y;


    public Theatre(String name, String description, double x, double y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
