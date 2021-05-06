package com.binobook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Order Items Model")
public class OrderItemsModel {

    @ApiModelProperty(value = "Order Id")
    private Integer order_id;
    @ApiModelProperty(value = "Book Id")
    private Integer bookID;

    @ApiModelProperty(value = "numberOrdered")
    private Integer numberOrdered;

    @ApiModelProperty(value = "Price")
    private Double price;

    public OrderItemsModel(Integer order_id, Integer bookID, Integer numberOrdered, Double price) {
        this.order_id = order_id;
        this.bookID = bookID;
        this.numberOrdered = numberOrdered;
        this.price = price;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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
