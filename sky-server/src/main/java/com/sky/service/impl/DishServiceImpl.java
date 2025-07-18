package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 清理redis缓存
     * @param pattern
     */
    public void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Override
    @Transactional
    public void saveWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setStatus(0);
        //添加菜品 并将菜品插入后的id映射回dish对象的id
        dishMapper.insert(dish);
        //添加口味
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        if(dishFlavors != null && !dishFlavors.isEmpty()){
            dishFlavors.forEach(flavor -> {
                flavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(dishFlavors);
        }
        //清理redis缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.selectPage(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量删除菜品
     * @param ids
     */
    @Override
    @Transactional
    public void deleteWithFlavorsByIds(List<Long> ids) {
        //查询菜品起售状态
        List<Integer> statusList = dishMapper.selectStatusByIds(ids);
        //起售中的菜品不能删除
        statusList.forEach(status -> {
            if(status == 1){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        });
        //查询当前菜品ids关联的套餐
        List<SetmealDish> setmealDishList = setMealDishMapper.selectByDishIds(ids);
        //如果菜品关联了套餐 则不能删除
        if(setmealDishList != null && !setmealDishList.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //删除菜品及其对应的口味
        //删除口味
        dishFlavorMapper.deleteByDishIds(ids);
        //删除菜品
        dishMapper.deleteByIds(ids);
        //清理redis缓存数据
        String key = "dish_*";
        cleanCache(key);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Override
    @Transactional
    public DishVO getWithFlavorsById(Long id) {
        //根据id查询菜品及其分类名称
        DishVO dishVO = dishMapper.selectWithCategoryNameById(id);
        //根据菜品id查询口味
        List<DishFlavor> dishFlavorList = dishFlavorMapper.selectByDishId(id);
        dishVO.setFlavors(dishFlavorList);
        return dishVO;
    }

    /**
     * 修改菜品
     * @param dishDTO
     */
    @Override
    @Transactional
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //修改菜品基本信息
        dishMapper.update(dish);
        //修改菜品口味 先删除 再添加
        List<Long> ids = List.of(dish.getId());
        //删除菜品口味
        dishFlavorMapper.deleteByDishIds(ids);
        //给菜品口味绑定上菜品id
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(flavor -> {
                flavor.setDishId(dishDTO.getId());
            });
            //添加菜品口味
            dishFlavorMapper.insertBatch(flavors);
        }
        //清理redis缓存
        String key = "dish_*";
        cleanCache(key);
    }

    /**
     * 菜品起售/停售
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = new Dish();
        dish.setStatus(status);
        dish.setId(id);
        dishMapper.update(dish);
        //如果菜品停售 则该菜品关联的套餐停售
        if(status == 0){
            List<Long> dishIds = new ArrayList<>();
            dishIds.add(id);
            List<SetmealDish> setmealDishList = setMealDishMapper.selectByDishIds(dishIds);
            List<Long> setmealIds = new ArrayList<>();
            setmealDishList.forEach(setmealDish -> {
                setmealIds.add(setmealDish.getSetmealId());
            });
            if(setmealIds != null && !setmealIds.isEmpty()){
                setmealMapper.stopByIds(setmealIds);
            }
        }
        String key = "dish_*";
        cleanCache(key);
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> listDishes(Long categoryId) {
        List<Dish> dishList = dishMapper.selectByCategoryId(categoryId);
        return dishList;
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        //生成redis的key 格式:
        /*
            key: dish_分类id
            value: List<DishVO>
         */
        String key = "dish_" + dish.getCategoryId();
        //查询redis中是否存在要查询的菜品列表
        List<DishVO> dishVOList = (List<DishVO>) redisTemplate.opsForValue().get(key);
        //如果有就直接返回缓存数据
        if(dishVOList != null && !dishVOList.isEmpty()){
            return dishVOList;
        }

        List<Dish> dishList = dishMapper.list(dish);

        dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.selectByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        //如果没有 把数据加入到redis缓存
        redisTemplate.opsForValue().set(key, dishVOList);

        return dishVOList;
    }
}
