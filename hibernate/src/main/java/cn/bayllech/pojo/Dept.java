package cn.bayllech.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class Dept {
  private Integer id;
  private String deptname;

  private Set<Employment> employmentSet;

}
