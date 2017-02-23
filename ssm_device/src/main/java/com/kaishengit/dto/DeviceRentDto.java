package com.kaishengit.dto;

import java.util.List;

public class DeviceRentDto  {

    /**
     * deviceArray : [{"id":"2","name":"挖掘机","unit":"辆","price":"300","num":"1","total":300},{"id":"4","name":"10米钢管","unit":"根","price":"40","num":"1","total":40}]
     * fileArray : ["8a3d354e-ded6-4e94-ac11-444a93cf5218.lrc","6a62ae75-b222-4d2d-a2a1-7caf0e3be8ad.lrc"]
     * companyName : 备有科技公司
     * linkMan : 齐备
     * tel : 123123123
     * cardNum : 4431223123
     * address : 世纪大道
     * fax : 098-23121
     * rentDate : 2017-02-17
     * backDate : 2017-02-19
     * totalDate : 2
     */

    private String companyName;
    private String linkMan;
    private String tel;
    private String cardNum;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDate;

    private Float totalPrice;
    private Float preCost;
    private Float lastCost;

    private List<DeviceArrayBean> deviceArray;

    private List<DocBean> fileArray;


    public List<DocBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<DocBean> fileArray) {
        this.fileArray = fileArray;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getPreCost() {
        return preCost;
    }

    public void setPreCost(Float preCost) {
        this.preCost = preCost;
    }

    public Float getLastCost() {
        return lastCost;
    }

    public void setLastCost(Float lastCost) {
        this.lastCost = lastCost;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }


    public static class DeviceArrayBean {
        /**
         * id : 2
         * name : 挖掘机
         * unit : 辆
         * price : 300
         * num : 1
         * total : 300
         */

        private Integer id;
        private String name;
        private String unit;
        private Float price;
        private Float num;
        private Float total;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getNum() {
            return num;
        }

        public void setNum(Float num) {
            this.num = num;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    public static class DocBean {
        private String sourceFileName;
        private String newFileName;

        public String getSourceFileName() {
            return sourceFileName;
        }

        public void setSourceFileName(String sourceFileName) {
            this.sourceFileName = sourceFileName;
        }

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }
    }
}
