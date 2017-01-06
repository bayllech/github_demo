package com.kaishengit.pojo;

/**
 * Created by bayllech on 2017/1/5.
 */
public class School {
    private Integer id;
    private String college;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", college='" + college + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
