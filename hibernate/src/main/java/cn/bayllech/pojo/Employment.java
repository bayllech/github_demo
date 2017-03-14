package cn.bayllech.pojo;

import lombok.Data;

@Data
public class Employment {

  private Integer id;
  private String name;
  private Integer deptid;

  private Dept dept;

}
