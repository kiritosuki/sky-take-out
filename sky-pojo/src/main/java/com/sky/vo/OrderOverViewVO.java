package com.sky.vo;

import java.io.Serializable;

/**
 * 订单概览数据
 */
//@Builder
public class OrderOverViewVO implements Serializable {
    //待接单数量
    private Integer waitingOrders;

    //待派送数量
    private Integer deliveredOrders;

    //已完成数量
    private Integer completedOrders;

    //已取消数量
    private Integer cancelledOrders;

    //全部订单
    private Integer allOrders;

    @Override
    public String toString() {
        return "OrderOverViewVO{" +
                "waitingOrders=" + waitingOrders +
                ", deliveredOrders=" + deliveredOrders +
                ", completedOrders=" + completedOrders +
                ", cancelledOrders=" + cancelledOrders +
                ", allOrders=" + allOrders +
                '}';
    }

    public Integer getWaitingOrders() {
        return waitingOrders;
    }

    public void setWaitingOrders(Integer waitingOrders) {
        this.waitingOrders = waitingOrders;
    }

    public Integer getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(Integer deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Integer getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(Integer cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public Integer getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(Integer allOrders) {
        this.allOrders = allOrders;
    }

    public OrderOverViewVO() {
    }

    public OrderOverViewVO(Integer waitingOrders, Integer deliveredOrders, Integer completedOrders, Integer cancelledOrders, Integer allOrders) {
        this.waitingOrders = waitingOrders;
        this.deliveredOrders = deliveredOrders;
        this.completedOrders = completedOrders;
        this.cancelledOrders = cancelledOrders;
        this.allOrders = allOrders;
    }
}
