package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.JwtProperties;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    //微信服务器地址
    private static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 微信用户登录
     * @param userLoginDTO
     * @return
     */
    @Transactional
    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        //获取openid
        String openid = getOpenid(userLoginDTO.getCode());
        //判断openid是否为空
        if(openid == null || openid.isEmpty()){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //判断是否为新用户
        User user = userMapper.getByOpenid(openid);
        if(user == null){
            //如果是新用户 需要自动注册
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            //保存用户信息 并返回映射id到该user对象
            userMapper.insert(user);
        }
        //为该用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        return new UserLoginVO(user.getId(), openid, token);
    }

    /**
     * 通过微信授权码code获得openid
     * @param code
     * @return
     */
    public String getOpenid(String code){
        //创建get请求
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //向微信登录服务器发送get请求
        String resultJson = HttpClientUtil.doGet(WX_LOGIN_URL, map);
        //将返回的字符串格式json转化为对象格式
        JSONObject jsonObject = JSON.parseObject(resultJson);
        //获取openid
        return jsonObject.getString("openid");
    }
}
