package com.sky.dto;


import java.io.Serializable;

public class OrdersRejectionDTO implements Serializable {

    private Long id;

    //订单拒绝原因
    private String rejectionReason;

    @Override
    public String toString() {
        return "OrdersRejectionDTO{" +
                "id=" + id +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
