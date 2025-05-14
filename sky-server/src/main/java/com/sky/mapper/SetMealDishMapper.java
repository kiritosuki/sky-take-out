package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * 通过菜品ids查询菜品关联的套餐
     * @param dishIds
     * @return
     */
    List<SetmealDish> selectByDishIds(List<Long> dishIds);

}
