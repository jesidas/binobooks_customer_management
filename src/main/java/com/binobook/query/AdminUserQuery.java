package com.binobook.query;

import com.binobook.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "AdminUser Query Module")
public class AdminUserQuery extends BaseQuery {

    @ApiModelProperty(value = "AdminUser Name")
    private String name;
    @ApiModelProperty(value = "AdminUser Email")
    private String email;
    @ApiModelProperty(value = "AdminUser Phone")
    private String phone;

    public AdminUserQuery(String name, String email, String phone) {
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
}
