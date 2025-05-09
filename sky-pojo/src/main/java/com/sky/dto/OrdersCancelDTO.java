package com.sky.dto;


import java.io.Serializable;

public class OrdersCancelDTO implements Serializable {

    private Long id;
    //订单取消原因
    private String cancelReason;

    @Override
    public String toString() {
        return "OrdersCancelDTO{" +
                "id=" + id +
                ", cancelReason='" + cancelReason + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
