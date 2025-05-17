package com.sky.controller.admin;

import com.sky.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/shop")
@RestController
public class ShopController {

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @PutMapping("/{status}")
    public Result setShopStatus(@PathVariable("status") Integer status){

        return Result.success();
    }
}
