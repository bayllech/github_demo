package cn.bayllech.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deptname;

    @OneToMany(mappedBy = "dept")
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
