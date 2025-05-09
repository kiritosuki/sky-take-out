package com.sky.vo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Builder
public class OrderSubmitVO implements Serializable {
    //订单id
    private Long id;
    //订单号
    private String orderNumber;
    //订单金额
    private BigDecimal orderAmount;
    //下单时间
    private LocalDateTime orderTime;

    @Override
    public String toString() {
        return "OrderSubmitVO{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderTime=" + orderTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderSubmitVO() {
    }

    public OrderSubmitVO(Long id, String orderNumber, BigDecimal orderAmount, LocalDateTime orderTime) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderAmount = orderAmount;
        this.orderTime = orderTime;
    }
}
