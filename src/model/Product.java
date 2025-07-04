package model;

import java.time.LocalDate;

public class Product implements Shippable, Expirable {
    private String name;
    private double price;
    private int quantity;
    private Double weight;             
    private LocalDate expiryDate;      

    public Product(String name, double price, int quantity, Double weight, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.expiryDate = expiryDate;
    }
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String name, double price, int quantity, Double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }
    public Product(String name, double price, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int no) {
        if (no <= quantity) {
            quantity -= no;
        } else {
            throw new IllegalArgumentException("Out of stock" + name + " available quantity: " + quantity);
        }
    }

    @Override
    public boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public double getWeight() {
        if(weight == null) {
            return 0; 
        }
        return weight;
    }

    @Override
    public boolean requiresShipping() {
        return weight != null && weight > 0;
    }
}
