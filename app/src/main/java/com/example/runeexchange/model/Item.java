package com.example.runeexchange.model;


public class Item {
    private int id;
    private double priceWhenAdded;
    private double currentPrice;
    private String change;
    private String name;

    public Item(int id, double priceWhenAdded, String name) {
        this.id = id;
        this.priceWhenAdded = priceWhenAdded;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public double getPriceWhenAdded() {
        return priceWhenAdded;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getChange() {
        return change;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriceWhenAdded(double priceWhenAdded) {
        this.priceWhenAdded = priceWhenAdded;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", priceWhenAdded=" + priceWhenAdded +
                ", currentPrice=" + currentPrice +
                ", change='" + change + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
