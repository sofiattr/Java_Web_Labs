package org.example.model;

public class CatFood {
    private int id; // ДОДАЛИ
    private String brand;
    private String flavor;
    private double weightKg;
    private double price;
    private String imageUrl;

    // Оновлений конструктор
    public CatFood(int id, String brand, String flavor, double weightKg, double price, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.flavor = flavor;
        this.weightKg = weightKg;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Геттери та сеттери
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getBrand() { return brand; }
    public String getFlavor() { return flavor; }
    public double getWeightKg() { return weightKg; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}