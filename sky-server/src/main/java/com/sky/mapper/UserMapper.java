package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    /**
     * 根据openid获取User对象
     * @param openid
     * @return
     */
    @Select("select * from sky_take_out.user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 添加用户信息
     * @param user
     */
    void insert(User user);

    /**
     * 动态查询用户数量
     * @param map
     * @return
     */
    Integer countByMap(HashMap<String, Object> map);
}
