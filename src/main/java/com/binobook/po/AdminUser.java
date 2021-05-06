package com.binobook.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@ApiModel(description = "Response Result- adminUser Information")
public class AdminUser {
    @ApiModelProperty(value = "id",example = "0")
    private Integer id;
    @ApiModelProperty(value = "AdminUser Name")
    @NotBlank(message = "AdminUser Name cannot be empty！")
    private String name;
    @ApiModelProperty(value = "AdminUser Password")
    @NotBlank(message = "AdminUser password Cannot be empty！")
    private String userPassword;
    @ApiModelProperty(value = "True Name")
    private String trueName;
    @ApiModelProperty(value = "AdminUser Email")
    private String email;
    @ApiModelProperty(value = "AdminUser Phone")
    private String phone;

    @ApiModelProperty(value = "AdminUser Created Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @ApiModelProperty(value = "AdminUser Updated Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public AdminUser() {
    }

    public AdminUser(@NotBlank(message = "AdminUser Name cannot be empty！") String name, @NotBlank(message = "AdminUser password Cannot be empty！") String userPassword, String email, String phone){
        this.name = name;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", userName='" + name + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", trueName='" + trueName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", roleIds='" + roleIds + '\'' +
                '}';
    }

    private String roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getuserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}