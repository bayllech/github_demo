package cn.bayllech.pojo;

import java.util.Set;

public class Dept {
  private Integer id;
  private String deptname;

  private Set<Employment> employmentSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Set<Employment> getEmploymentSet() {
        return employmentSet;
    }

    public void setEmploymentSet(Set<Employment> employmentSet) {
        this.employmentSet = employmentSet;
    }
}
