package com.latihan.ezyfood_2301853962.model;

public class Item {
    private int img;
    private String name;
    private int price;
    private String type;

    public Item(int img, String name, int price, String type) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
