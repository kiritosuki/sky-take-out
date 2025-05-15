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

    /**
     * 新增菜品与套餐的关联关系
     * @param setmealDishList
     */
    void insertBatch(List<SetmealDish> setmealDishList);

    /**
     * 删除套餐下的菜品
     * @param setmealIds
     */
    void deleteBySetmealIds(List<Long> setmealIds);

    @Select("select * from sky_take_out.setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> selectBySetmealId(Long id);
}
