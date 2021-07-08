package com.nuce.model;

public class Image {
    private int id;
    private String image;
    private Product product;

    public Image() {
    }

    public Image(int id, String image, Product product) {
        this.id = id;
        this.image = image;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
