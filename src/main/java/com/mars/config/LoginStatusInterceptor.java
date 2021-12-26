package com.mars.config;

import com.mars.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginStatusInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否为空,如果为空代表未登录,提醒重新登录(401)
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //判断token看是否有效
        //解析没有异常,说明是登录状态
        if (JwtUtil.checkToken(token)) {
            return true;
        }else{
            //Token验证失败,说明未登录,提醒登录(401)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
