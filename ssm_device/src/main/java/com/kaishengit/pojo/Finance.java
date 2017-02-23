package com.kaishengit.pojo;

import lombok.Data;

@Data
public class Finance {

    public static final String TYPE_INCOME = "收入";
    public static final String TYPE_OUTGO = "支出";
    public static final String STATE_COMPLETE = "已完成";
    public static final String STATE_UNCOMPLETE = "未完成";
    public static final String MODULE_DEVICE = "设备租赁";
    public static final String MODULE_WORKOUT = "劳务派遣";
    public static final String REMARK_PRE = "预付款";
    public static final String REMARK_LAST = "尾款";

    private Integer id;
    private String financeSerial;
    private String serialNum;
    private String type;
    private Float money;
    private String state;
    private String module;
    private String createDate;
    private String createUser;
    private String confirmDate;
    private String confirmUser;
    private String remark;

}