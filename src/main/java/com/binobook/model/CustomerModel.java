package com.binobook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Customer Model")
public class CustomerModel {

    // private Integer userId;
    @ApiModelProperty(value = "Customer Name")
    private String name;
    @ApiModelProperty(value = "Customer True Name")
    private String trueName;
    @ApiModelProperty(value = "Encrypted UserId String")
    private String userIdStr; //


    /*public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    @Override
    public String toString() {
        return "CustomerModel{" +
                "userName='" + name + '\'' +
                ", trueName='" + trueName + '\'' +
                ", userIdStr='" + userIdStr + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }
}
