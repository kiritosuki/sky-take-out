package com.sky.dto;

import java.io.Serializable;

public class OrdersPaymentDTO implements Serializable {
    //订单号
    private String orderNumber;

    //付款方式
    private Integer payMethod;

    @Override
    public String toString() {
        return "OrdersPaymentDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", payMethod=" + payMethod +
                '}';
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }
}
