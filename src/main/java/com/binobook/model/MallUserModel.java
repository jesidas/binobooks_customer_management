package com.binobook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "MallUser Model")
public class MallUserModel {

    // private Integer userId;
    @ApiModelProperty(value = "MallUser Name")
    private String name;
    @ApiModelProperty(value = "MallUser True Name")
    private String trueName;
    @ApiModelProperty(value = "Encrypted UserId String")
    private String userIdStr;

    @Override
    public String toString() {
        return "MallUserModel{" +
                "name='" + name + '\'' +
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
