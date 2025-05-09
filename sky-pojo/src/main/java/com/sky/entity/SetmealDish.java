package com.sky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐菜品关系
 */
//@Builder
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //套餐id
    private Long setmealId;

    //菜品id
    private Long dishId;

    //菜品名称 （冗余字段）
    private String name;

    //菜品原价
    private BigDecimal price;

    //份数
    private Integer copies;

    @Override
    public String toString() {
        return "SetmealDish{" +
                "id=" + id +
                ", setmealId=" + setmealId +
                ", dishId=" + dishId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public SetmealDish() {
    }

    public SetmealDish(Long id, Long setmealId, Long dishId, String name, BigDecimal price, Integer copies) {
        this.id = id;
        this.setmealId = setmealId;
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.copies = copies;
    }
}
