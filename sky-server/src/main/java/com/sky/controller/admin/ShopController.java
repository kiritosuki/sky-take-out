package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/shop")
@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    /**
     * 设置店铺营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result setShopStatus(@PathVariable("status") Integer status){
        log.info("设置店铺营业状态为: {}", status == 1 ? "营业中" : "打烊中");
        shopService.setShopStatus(status);
        return Result.success();
    }

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
