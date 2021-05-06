package com.binobook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "AdminUser Model")
public class AdminUserModel {

    // private Integer userId;
    @ApiModelProperty(value = "AdminUser Name")
    private String name;
    @ApiModelProperty(value = "AdminUser True Name")
    private String trueName;
    @ApiModelProperty(value = "Encrypted UserId String")
    private String userIdStr; // Encrypted User Id



    @Override
    public String toString() {
        return "AdminUserModel{" +
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
