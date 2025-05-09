package com.sky.vo;


import java.io.Serializable;

/**
 * 数据概览
 */
//@Builder
public class BusinessDataVO implements Serializable {

    private Double turnover;//营业额

    private Integer validOrderCount;//有效订单数

    private Double orderCompletionRate;//订单完成率

    private Double unitPrice;//平均客单价

    private Integer newUsers;//新增用户数

    @Override
    public String toString() {
        return "BusinessDataVO{" +
                "turnover=" + turnover +
                ", validOrderCount=" + validOrderCount +
                ", orderCompletionRate=" + orderCompletionRate +
                ", unitPrice=" + unitPrice +
                ", newUsers=" + newUsers +
                '}';
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(Integer newUsers) {
        this.newUsers = newUsers;
    }

    public BusinessDataVO() {
    }

    public BusinessDataVO(Double turnover, Integer validOrderCount, Double orderCompletionRate, Double unitPrice, Integer newUsers) {
        this.turnover = turnover;
        this.validOrderCount = validOrderCount;
        this.orderCompletionRate = orderCompletionRate;
        this.unitPrice = unitPrice;
        this.newUsers = newUsers;
    }
}
