package com.kaishengit.entity;

import java.sql.Timestamp;
import java.io.Serializable;

public class User implements Serializable {

    //默认头像
    public static final String DEFAULT_AVATAR_NAME = "images.png";
    //用户状态未激活
    public static final Integer USERSTATE_UNACTIVE = 0;
    //用户状态已激活
    public static final Integer USERSTATE_ACTIVE = 1;
    //用户禁用
    public static final Integer USERSTATE_DISABLED = 2;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer state;
    private Timestamp createTime;
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

