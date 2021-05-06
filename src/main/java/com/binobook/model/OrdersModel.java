package com.binobook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Orders Model")
public class OrdersModel {

    @ApiModelProperty(value = "Order Id")
    private Integer order_id;

    @ApiModelProperty(value = "Customer Id")
    private Integer customer_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "Order Date")
    private Date orderDate;

    @ApiModelProperty(value = "SubTotal")
    private Double subTotal;

    public OrdersModel(Integer order_id, Integer customer_id, Date orderDate, Double subTotal) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "OrdersModel{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", orderDate=" + orderDate +
                ", subTotal=" + subTotal +
                '}';
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
