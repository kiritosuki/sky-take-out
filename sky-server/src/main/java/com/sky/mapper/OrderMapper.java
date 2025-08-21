package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据用户id和订单号查询订单
     * @param userId
     * @param orderNumber
     * @return
     */
    @Select("select o.* from orders o where o.user_id = #{userId} and o.number = #{orderNumber}")
    Orders getByUserIdAndOrderNumber(@Param("userId") Long userId, @Param("orderNumber") String orderNumber);

    /**
     * 更新数据库订单状态
     * @param orders
     */
    void update(Orders orders);

    /**
     * 查询订单列表
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> list(OrdersPageQueryDTO ordersPageQueryDTO);
}
