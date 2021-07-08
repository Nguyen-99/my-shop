package com.nuce.model;

public class OrderItem {
    private int id;
    private DetailProduct detailProduct;
    int number;
    private Bill bill;

    public OrderItem() {
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

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
