package com.binobook.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(description = "Response Result- Order Items Information")
public class OrderItems {

    private Integer orderId;

    private Integer bookID;

    private Integer numberOrdered;

    private Double price;

    public OrderItems(Integer orderId, Integer bookID, Integer numberOrdered, Double price) {
        this.orderId = orderId;
        this.bookID = bookID;
        this.numberOrdered = numberOrdered;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderId=" + orderId +
                ", bookID=" + bookID +
                ", numberOrdered=" + numberOrdered +
                ", price=" + price +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getNumberOrdered() {
        return numberOrdered;
    }

    public void setNumberOrdered(Integer numberOrdered) {
        this.numberOrdered = numberOrdered;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}