package com.nuce.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bill {
    private int id;
    private Customer customer;
    private Date createDate;
    private String deliveryAddress;
    private String status;
    private List<OrderItem> orderItems;

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getBillMoney(){
        double moneyBill=0;
        for (OrderItem orderItem:orderItems) {
            moneyBill+=orderItem.getItemMoney();
        }
        return moneyBill;
    }

    public String getDate(){
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String date=dateFormat.format(createDate);
        return date;
    }
}
