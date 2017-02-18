package com.kaishengit.pojo;

import lombok.Data;

@Data
public class DeviceRentDetail {
    private Integer id;
    private String deviceName;
    private String deviceUnit;
    private Float devicePrice;
    private Float num;
    private Float totalPrice;
    private Integer rentId;
}
