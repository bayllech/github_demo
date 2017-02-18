package com.kaishengit.pojo;

import lombok.Data;

@Data
public class WorkTypeDetail {
    private Integer id;
    private String workTypeName;
    private Float workTypePrice;
    private Float num;
    private Float totalPrice;
    private Integer rentId;
}
