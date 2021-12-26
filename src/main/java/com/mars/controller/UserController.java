package com.mars.controller;

import com.mars.entity.AdminUser;
import com.mars.entity.MallUser;
import com.mars.service.AdminUserService;
import com.mars.service.MallUserService;
import com.mars.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private MallUserService mallUserService;

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("login")
    public Result login(@RequestBody MallUser mallUser) {
        if (mallUser != null) {
            String token = mallUserService.login(mallUser);
            if (token!=null){
                return new Result(200L,"登录成功",token);
            }
        }
        return new Result(402L,"登录失败",null);
    }

    public Result loginAdmin(AdminUser adminUser) {
        return null;
    }
}
