package com.example.runeexchange.model;

import java.text.DecimalFormat;

public class ItemAsFavourite {
    private int id;
    private double priceWhenAdded;
    private double currentPrice;
    private String change;
    private String name;

    public ItemAsFavourite(int id, double priceWhenAdded, String name) {
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

    public double getCurrentPrice() { return currentPrice; }

    public String getChange() {
        if(currentPrice != 0 || priceWhenAdded != 0){
            double tempChange = currentPrice / priceWhenAdded * 100;
            DecimalFormat df = new DecimalFormat("#.##");
            if(tempChange>100){
                return "+" + String.valueOf(df.format(tempChange)) + "%";
            }
            else{
                return "-" + String.valueOf(df.format(tempChange)) + "%";
            }
        }
        return "0";
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
        return "ItemAsFavourite{" +
                "id=" + id +
                ", priceWhenAdded=" + priceWhenAdded +
                ", currentPrice=" + currentPrice +
                ", change='" + change + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
