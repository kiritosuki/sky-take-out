package com.sky.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 自定义定时任务 用于订单状态定时处理
 */
@Component
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    private static final Logger log = LoggerFactory.getLogger(OrderTask.class);

    /**
     * 处理支付超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void processTimeoutOrder(){
        //每分钟检查一次 待支付状态超过15min自动取消订单
        log.info("处理支付超时订单: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> orderList = orderMapper.getByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT, time);
        if(orderList != null && !orderList.isEmpty()){
            for(Orders order : orderList){
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时 自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }

    /**
     * 处理派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        //每天凌晨一点检查一次 派送时间超过1h的订单设置为已完成
        log.info("处理派送中的订单: {}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> orderList = orderMapper.getByStatusAndOrdertimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if(orderList != null && !orderList.isEmpty()){
            for(Orders order : orderList){
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            }
        }
    }
}
