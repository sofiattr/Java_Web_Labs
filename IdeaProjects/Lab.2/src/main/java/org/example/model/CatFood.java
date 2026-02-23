package org.example.model;

public class CatFood {
    private String brand;
    private String flavor;
    private double weightKg;
    private double price;
    private String imageUrl; //
    public CatFood(String brand, String flavor, double weightKg, double price, String imageUrl) {
        this.brand = brand;
        this.flavor = flavor;
        this.weightKg = weightKg;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}