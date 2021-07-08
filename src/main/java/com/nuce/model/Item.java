package com.nuce.model;

public class Item {
    private int id;
    private DetailProduct detailProduct;
    private int number;
    private Customer customer;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetailProduct getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(DetailProduct detailProduct) {
        this.detailProduct = detailProduct;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getItemMoney(){
        return detailProduct.getProduct().getPrice()*number;
    }
}
