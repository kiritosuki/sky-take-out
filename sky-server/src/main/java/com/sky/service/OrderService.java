package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 跳过微信支付
     * @param ordersPaymentDTO
     */
    void payment(OrdersPaymentDTO ordersPaymentDTO);

    /**
     * 历史订单查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult listOrders(Integer page, Integer pageSize, Integer status);

    /**
     * 查询订单详细信息
     * @param id
     * @return
     */
    OrderVO orderDetail(Long id);

    /**
     * 取消订单
     * @param id
     */
    void cancelOrder(Long id);
}
