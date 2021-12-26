package com.mars.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mars.entity.MallUser;

public interface MallUserService extends IService<MallUser> {
    String login(MallUser mallUser);
}
