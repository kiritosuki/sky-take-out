package com.sky.vo;

import java.io.Serializable;

//@Builder
public class OrderReportVO implements Serializable {

    //日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //每日订单数，以逗号分隔，例如：260,210,215
    private String orderCountList;

    //每日有效订单数，以逗号分隔，例如：20,21,10
    private String validOrderCountList;

    //订单总数
    private Integer totalOrderCount;

    //有效订单数
    private Integer validOrderCount;

    //订单完成率
    private Double orderCompletionRate;

    @Override
    public String toString() {
        return "OrderReportVO{" +
                "dateList='" + dateList + '\'' +
                ", orderCountList='" + orderCountList + '\'' +
                ", validOrderCountList='" + validOrderCountList + '\'' +
                ", totalOrderCount=" + totalOrderCount +
                ", validOrderCount=" + validOrderCount +
                ", orderCompletionRate=" + orderCompletionRate +
                '}';
    }

    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public String getOrderCountList() {
        return orderCountList;
    }

    public void setOrderCountList(String orderCountList) {
        this.orderCountList = orderCountList;
    }

    public String getValidOrderCountList() {
        return validOrderCountList;
    }

    public void setValidOrderCountList(String validOrderCountList) {
        this.validOrderCountList = validOrderCountList;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Integer getValidOrderCount() {
        return validOrderCount;
    }

    public void setValidOrderCount(Integer validOrderCount) {
        this.validOrderCount = validOrderCount;
    }

    public Double getOrderCompletionRate() {
        return orderCompletionRate;
    }

    public void setOrderCompletionRate(Double orderCompletionRate) {
        this.orderCompletionRate = orderCompletionRate;
    }

    public OrderReportVO() {
    }

    public OrderReportVO(String dateList, String orderCountList, String validOrderCountList, Integer totalOrderCount, Integer validOrderCount, Double orderCompletionRate) {
        this.dateList = dateList;
        this.orderCountList = orderCountList;
        this.validOrderCountList = validOrderCountList;
        this.totalOrderCount = totalOrderCount;
        this.validOrderCount = validOrderCount;
        this.orderCompletionRate = orderCompletionRate;
    }
}
