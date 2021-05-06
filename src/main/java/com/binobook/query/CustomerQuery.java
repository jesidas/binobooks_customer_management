package com.binobook.query;

import com.binobook.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Customer Query Module ")
public class CustomerQuery extends BaseQuery {

    @ApiModelProperty(value = "Customer Name")
    private String name;
    @ApiModelProperty(value = "Customer Email")
    private String email;
    @ApiModelProperty(value = "Customer Phone")
    private String phone;

    public CustomerQuery(String name, String email, String phone) {
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
        return "CustomerQuery{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
