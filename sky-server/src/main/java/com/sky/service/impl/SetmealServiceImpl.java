package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private DishMapper dishMapper;

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
        //查询套餐起售状态
        List<Integer> statusList = setmealMapper.selectStatusByIds(ids);
        statusList.forEach(status -> {
            if(status == 1){
                //起售中的套餐不能删除
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
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

    /**
     * 套餐起售/停售
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        //声名套餐对象
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        setmeal.setId(id);
        //获取套餐关联的菜品列表
        List<SetmealDish> setmealDishList = setMealDishMapper.selectBySetmealId(id);
        List<Long> dishIdList = new ArrayList<>();
        setmealDishList.forEach(setmealDish -> {
            //通过菜品列表中的菜品 获取菜品id 添加到菜品id列表中
            dishIdList.add(setmealDish.getDishId());
        });
        //通过菜品id列表 获取菜品起售状态列表
        List<Integer> statusList = dishMapper.selectStatusByIds(dishIdList);
        statusList.forEach(dishStatus -> {
            if(dishStatus == 0){
                //套餐中有未起售的菜品 无法起售套餐
                throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
            }
        });
        setmealMapper.update(setmeal);
    }
}
