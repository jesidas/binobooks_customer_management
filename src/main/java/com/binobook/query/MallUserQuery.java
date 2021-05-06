package com.binobook.query;

import com.binobook.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "MallUser Query Module ")
public class MallUserQuery extends BaseQuery {

    @ApiModelProperty(value = "MallUser Name")
    private String name;
    @ApiModelProperty(value = "MallUser Email")
    private String email;
    @ApiModelProperty(value = "MallUser Phone")
    private String phone;

    public MallUserQuery(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MallUserQuery{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
