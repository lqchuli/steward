package com.lingyu.steward.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.lingyu.steward.api.common.ApiConstant;
import com.lingyu.steward.api.common.ApiResult;
import com.lingyu.steward.api.common.ApiResultCodeEnum;
import com.lingyu.steward.api.common.AppSysRequestData;
import com.lingyu.steward.api.utils.SignBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * APP Api签名验证过滤器
 * 算法说明:
 * 所有业务参数（除去sign）的key转换成小写以后按自然数序排序，然后按照key+value的形式拼接
 * 并在收尾添加{@link ApiConstant}中的secretKey
 * 得到新的字符串以后MD5加密得到
 *
 * @author allan
 * @date 12/11/2017
 */
public class AppApiInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //系统级参数
        AppSysRequestData requestData = new AppSysRequestData();
        requestData.setAppVersion(request.getHeader(AppSysRequestData.APP_VERSION_KEY));
        // TODO: 14/11/2017 AppSysRequestData 待添加
        request.setAttribute("appSysRequestData", requestData);

        //签名
        String requestSign = request.getParameter("sign");
        if (StringUtils.isEmpty(requestSign)) {
            response.getWriter().write(JSON.toJSONString(ApiResult.errorWith(ApiResultCodeEnum.EMPTY_SIGN)));
            return false;
        }
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, String> paramMapToSign = new TreeMap<>();
        paramMap.forEach((key, value) -> {
            if (!"sign".equals(key)) {
                if (value != null && value.length > 0) {
                    paramMapToSign.put(key, value[0]);
                }
            }
        });
        String sign = SignBuilder.build(paramMapToSign, ApiConstant.SECRET_KEY, ApiConstant.SECRET_KEY);
        if (!sign.equals(requestSign)) {
            response.getWriter().write(JSON.toJSONString(ApiResult.errorWith(ApiResultCodeEnum.SIGN_ERROR)));
            return false;
        }

        return true;
    }
}
