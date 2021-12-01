package com.qa.ims.persistence.domain;

public class Item {
    private Long id;
    private String name;
    private Double value;

    public Item(String name, Double value) {
        this.setName(name);
        this.setValue(value);
    }

    public Item(Long id, String name, Double value) {
        this.setId(id);
        this.setName(name);
        this.setValue(value);
    }

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " value:" + value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
