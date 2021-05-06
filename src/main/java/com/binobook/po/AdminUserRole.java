package com.binobook.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@ApiModel(description = "Response Result- userRole")
public class AdminUserRole {
    @ApiModelProperty(value = "id",example = "0")
    private Integer id;

    @ApiModelProperty(value = "AdminUser id")
    @NotBlank(message = "AdminUser id cannot be empty！")
    private Integer userId;

    @ApiModelProperty(value = "Role id")
    private Integer roleId;

    @ApiModelProperty(value = "AdminUser Created Time")
    private Date createDate;

    @ApiModelProperty(value = "AdminUser Updated Time")
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}