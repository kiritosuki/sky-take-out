package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void insert(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        setmeal.setStatus(0);
        setmealMapper.insert(setmeal);
        if (setmealDishList != null && !setmealDishList.isEmpty()) {
            setmealDishList.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmeal.getId());
            });
            setMealDishMapper.insertBatch(setmealDishList);
        }
    }

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> setmealVOPage = setmealMapper.selectPage(setmealPageQueryDTO);
        return new PageResult(setmealVOPage.getTotal(), setmealVOPage.getResult());
    }

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        //批量删除套餐
        setmealMapper.deleteByIds(ids);
        //批量删除套餐下的菜品
        setMealDishMapper.deleteBySetmealIds(ids);
    }

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        //更改套餐基本信息
        setmealMapper.update(setmeal);
        //删除原本套餐绑定的菜品
        List<Long> setmealIds = List.of(setmeal.getId());
        setMealDishMapper.deleteBySetmealIds(setmealIds);
        //增加现在套餐下的关联菜品
        if (setmealDishList != null && !setmealDishList.isEmpty()) {
            setmealDishList.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmeal.getId());
            });
            setMealDishMapper.insertBatch(setmealDishList);
        }
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @Override
    @Transactional
    public SetmealVO selectById(Long id) {
        //查询套餐基本信息
        SetmealVO setmealVO = setmealMapper.selectById(id);
        //查询套餐关联菜品
        List<SetmealDish> setmealDishList = setMealDishMapper.selectBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }
}
