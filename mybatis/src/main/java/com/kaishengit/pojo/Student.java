package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by bayllech on 2017/1/5.
 */
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private Integer schoolid;

    private School school;

    public Student () {}

    public Student (String name,String address) {
        this.name = name;
        this.address = address;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

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
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", schoolid=" + schoolid +
                '}';
    }
}
