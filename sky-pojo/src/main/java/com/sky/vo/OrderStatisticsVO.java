package com.sky.vo;

import java.io.Serializable;

public class OrderStatisticsVO implements Serializable {
    //待接单数量
    private Integer toBeConfirmed;

    //待派送数量
    private Integer confirmed;

    //派送中数量
    private Integer deliveryInProgress;

    @Override
    public String toString() {
        return "OrderStatisticsVO{" +
                "toBeConfirmed=" + toBeConfirmed +
                ", confirmed=" + confirmed +
                ", deliveryInProgress=" + deliveryInProgress +
                '}';
    }

    public Integer getToBeConfirmed() {
        return toBeConfirmed;
    }

    public void setToBeConfirmed(Integer toBeConfirmed) {
        this.toBeConfirmed = toBeConfirmed;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeliveryInProgress() {
        return deliveryInProgress;
    }

    public void setDeliveryInProgress(Integer deliveryInProgress) {
        this.deliveryInProgress = deliveryInProgress;
    }
}
