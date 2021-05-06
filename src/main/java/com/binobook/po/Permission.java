package com.binobook.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel(description = "Response Result- Permission")
public class Permission {
    @ApiModelProperty(value = "id",example = "0")
    private Integer id;

    @ApiModelProperty(value = "roleId",example = "0")
    private Integer roleId;

    @ApiModelProperty(value = "moduleId",example = "0")
    private Integer moduleId;

    @ApiModelProperty(value = "aclValue",example = "0")
    private String aclValue;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getAclValue() {
        return aclValue;
    }

    public void setAclValue(String aclValue) {
        this.aclValue = aclValue == null ? null : aclValue.trim();
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