package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

    private Long id;
    private String name;
    private Double price;

    public Item(String name, double price) {
        this.setName(name);
        this.setPrice(price);
    }

    public Item(Long id, String name, double price) {
        this(name, price);
        this.setId(id);
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " price:" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
