package com.sky.vo;


import java.io.Serializable;

/**
 * 套餐总览
 */
//@Builder
public class SetmealOverViewVO implements Serializable {
    // 已启售数量
    private Integer sold;

    // 已停售数量
    private Integer discontinued;

    @Override
    public String toString() {
        return "SetmealOverViewVO{" +
                "sold=" + sold +
                ", discontinued=" + discontinued +
                '}';
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Integer discontinued) {
        this.discontinued = discontinued;
    }

    public SetmealOverViewVO() {
    }

    public SetmealOverViewVO(Integer sold, Integer discontinued) {
        this.sold = sold;
        this.discontinued = discontinued;
    }
}
