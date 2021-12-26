package com.mars.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mars.entity.AdminUser;
import com.mars.mapper.AdminUserMapper;
import com.mars.service.AdminUserService;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService  {

}
