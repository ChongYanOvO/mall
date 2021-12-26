package com.mars.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mars.entity.MallUser;
import com.mars.mapper.MallUserMapper;
import com.mars.service.MallUserService;
import com.mars.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class MallUserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements MallUserService {

    @Override
    public String  login(MallUser mallUser) {
        LambdaQueryWrapper<MallUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MallUser::getLoginUserName, mallUser.getLoginUserName()).eq(MallUser::getLoginPassword, mallUser.getLoginPassword());
        MallUser user = getBaseMapper().selectOne(wrapper);
        if (user != null) {
            return JwtUtil.createToken(JSONObject.parseObject(JSON.toJSONString(user)));
        }else {
            return null;
        }
    }
}
