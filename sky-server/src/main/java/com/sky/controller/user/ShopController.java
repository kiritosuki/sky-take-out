package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/shop")
@RestController("userShopController")
public class ShopController {
    @Autowired
    private ShopService shopService;

    private static final Logger log = LoggerFactory.getLogger(com.sky.controller.admin.ShopController.class);

    /**
     * 获取店铺营业状态
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getShopStatus(){
        Integer status = shopService.getShopStatus();
        log.info("获取店铺营业状态为: {}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }

}