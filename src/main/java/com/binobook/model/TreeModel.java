package com.binobook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Tree Model")
public class TreeModel {
    @ApiModelProperty(value = "Encrypted id String")
    private Integer id;
    @ApiModelProperty(value = "Encrypted pId String")
    private Integer pId;
    @ApiModelProperty(value = "name")
    private String name;
    private boolean checked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
