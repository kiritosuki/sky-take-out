package com.sky.vo;


import java.io.Serializable;

//@Builder
public class TurnoverReportVO implements Serializable {

    //日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //营业额，以逗号分隔，例如：406.0,1520.0,75.0
    private String turnoverList;

    @Override
    public String toString() {
        return "TurnoverReportVO{" +
                "dateList='" + dateList + '\'' +
                ", turnoverList='" + turnoverList + '\'' +
                '}';
    }

    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public String getTurnoverList() {
        return turnoverList;
    }

    public void setTurnoverList(String turnoverList) {
        this.turnoverList = turnoverList;
    }

    public TurnoverReportVO() {
    }

    public TurnoverReportVO(String dateList, String turnoverList) {
        this.dateList = dateList;
        this.turnoverList = turnoverList;
    }
}
