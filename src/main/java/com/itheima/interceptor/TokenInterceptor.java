/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.interceptor;

import com.itheima.util.CurrentHolder;
import com.itheima.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求路径
        String requestURL = request.getRequestURI();
        // 判断是否是登录请求，如果路径中包含 /login， 说明是登录请求，放行
        if (requestURL.contains("/login")) {
            log.info("登录请求，放行");
            return true;
        }
        // 获取请求头的token
        String token = request.getHeader("token");
        // 判断token是否存在，如果不存在，说明用户没有登录返回错误信息
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 如果token存在，校验令牌，失败返回错误信息
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工ID：{}", empId);
        } catch (Exception e) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 校验通过放行
        log.info("令牌合法，放行");
        return true;
    }
}
