package com.sky.vo;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderVO extends Orders implements Serializable {

    //订单菜品信息
    private String orderDishes;

    //订单详情
    private List<OrderDetail> orderDetailList;

    public OrderVO(String orderDishes, List<OrderDetail> orderDetailList) {
        this.orderDishes = orderDishes;
        this.orderDetailList = orderDetailList;
    }

    public OrderVO() {
    }

    public OrderVO(Long id, String number, Integer status, Long userId, Long addressBookId, LocalDateTime orderTime, LocalDateTime checkoutTime, Integer payMethod, Integer payStatus, BigDecimal amount, String remark, String userName, String phone, String address, String consignee, String cancelReason, String rejectionReason, LocalDateTime cancelTime, LocalDateTime estimatedDeliveryTime, Integer deliveryStatus, LocalDateTime deliveryTime, int packAmount, int tablewareNumber, Integer tablewareStatus) {
        super(id, number, status, userId, addressBookId, orderTime, checkoutTime, payMethod, payStatus, amount, remark, userName, phone, address, consignee, cancelReason, rejectionReason, cancelTime, estimatedDeliveryTime, deliveryStatus, deliveryTime, packAmount, tablewareNumber, tablewareStatus);
    }

    public OrderVO(Long id, String number, Integer status, Long userId, Long addressBookId, LocalDateTime orderTime, LocalDateTime checkoutTime, Integer payMethod, Integer payStatus, BigDecimal amount, String remark, String userName, String phone, String address, String consignee, String cancelReason, String rejectionReason, LocalDateTime cancelTime, LocalDateTime estimatedDeliveryTime, Integer deliveryStatus, LocalDateTime deliveryTime, int packAmount, int tablewareNumber, Integer tablewareStatus, String orderDishes, List<OrderDetail> orderDetailList) {
        super(id, number, status, userId, addressBookId, orderTime, checkoutTime, payMethod, payStatus, amount, remark, userName, phone, address, consignee, cancelReason, rejectionReason, cancelTime, estimatedDeliveryTime, deliveryStatus, deliveryTime, packAmount, tablewareNumber, tablewareStatus);
        this.orderDishes = orderDishes;
        this.orderDetailList = orderDetailList;
    }

    public String getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(String orderDishes) {
        this.orderDishes = orderDishes;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderDishes='" + orderDishes + '\'' +
                ", orderDetailList=" + orderDetailList +
                '}';
    }


}
