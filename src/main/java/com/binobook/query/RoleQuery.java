package com.binobook.query;

import com.binobook.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Role Query Module")
public class RoleQuery extends BaseQuery {

    @ApiModelProperty(value = "Role Phone")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
