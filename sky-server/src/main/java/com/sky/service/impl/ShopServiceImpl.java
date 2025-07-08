package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    private final static String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置店铺营业状态
     * @param status
     */
    @Override
    public void setShopStatus(Integer status) {
        ValueOperations<String, Object> redisForValue = redisTemplate.opsForValue();
        redisForValue.set(KEY, status);
    }

    /**
     * 获取店铺营业状态
     * @return
     */
    @Override
    public Integer getShopStatus() {
        ValueOperations<String, Object> redisForValue = redisTemplate.opsForValue();
        Integer status = (Integer) redisForValue.get(KEY);
        return status;
    }
}
