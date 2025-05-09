package com.sky.vo;

import java.io.Serializable;

//@Builder
public class SalesTop10ReportVO implements Serializable {

    //商品名称列表，以逗号分隔，例如：鱼香肉丝,宫保鸡丁,水煮鱼
    private String nameList;

    //销量列表，以逗号分隔，例如：260,215,200
    private String numberList;

    @Override
    public String toString() {
        return "SalesTop10ReportVO{" +
                "nameList='" + nameList + '\'' +
                ", numberList='" + numberList + '\'' +
                '}';
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getNumberList() {
        return numberList;
    }

    public void setNumberList(String numberList) {
        this.numberList = numberList;
    }

    public SalesTop10ReportVO() {
    }

    public SalesTop10ReportVO(String nameList, String numberList) {
        this.nameList = nameList;
        this.numberList = numberList;
    }
}
