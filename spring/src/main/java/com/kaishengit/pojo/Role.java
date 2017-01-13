package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by bayllech on 2017/1/13.
 */
public class Role implements Serializable {
    private Integer id;
    private String partRole;
    private String viewRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartRole() {
        return partRole;
    }

    public void setPartRole(String partRole) {
        this.partRole = partRole;
    }

    public String getViewRole() {
        return viewRole;
    }

    public void setViewRole(String viewRole) {
        this.viewRole = viewRole;
    }
}
