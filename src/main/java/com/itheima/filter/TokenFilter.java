/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.filter;

import com.itheima.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求路径
        String requestURL = request.getRequestURI();
        // 判断是否是登录请求，如果路径中包含 /login， 说明是登录请求，放行
        if (requestURL.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 获取请求头的token
        String token = request.getHeader("token");
        // 判断token是否存在，如果不存在，说明用户没有登录返回错误信息
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
        }

        // 如果token存在，校验令牌，失败返回错误信息
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
        }
        // 校验通过放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
        return;
    }
}
