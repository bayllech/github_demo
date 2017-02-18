package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkType implements Serializable{
    private Integer id;
    private String name;
    private Integer totalNum;
    private Integer currentNum;
    private Float price;
}
