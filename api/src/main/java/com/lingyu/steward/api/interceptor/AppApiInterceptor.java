package com.lingyu.steward.api.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * APP Api签名验证过滤器
 *
 * @author allan
 * @date 12/11/2017
 */
public class AppApiInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: 12/11/2017 签名验证算法逻辑待添加
        return false;
    }
}
