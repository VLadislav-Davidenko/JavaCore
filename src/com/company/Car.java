package com.company;

public abstract class Car implements Runnable {
    String name;
    int maxSpeed;


    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
