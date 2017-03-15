package cn.bayllech.pojo;

import lombok.Data;

public class Employment {

  private Integer id;
  private String name;
  private Integer deptid;

    @Override
    public String toString() {
        return "Employment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptid=" + deptid +
                ", dept=" + dept +
                '}';
    }

    private Dept dept;

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

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
