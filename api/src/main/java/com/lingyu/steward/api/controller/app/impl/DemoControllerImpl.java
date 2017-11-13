package com.lingyu.steward.api.controller.app.impl;

import com.lingyu.steward.api.common.ApiResult;
import com.lingyu.steward.api.common.ApiResultCodeEnum;
import com.lingyu.steward.api.common.AppSysRequestData;
import com.lingyu.steward.api.controller.app.DemoController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author allan
 * @date 14/11/2017
 */
@Controller
@RequestMapping(value = "/demo", method = RequestMethod.POST)
public class DemoControllerImpl implements DemoController {
    @Override
    @RequestMapping("/getDemo")
    @ResponseBody
    public ApiResult getDemo(
            @RequestAttribute() AppSysRequestData appSysRequestData
    ) {
        Map<String, String> mockResponse = new HashMap<>();
        mockResponse.put("name", "mockName");
        mockResponse.put("appVersion", appSysRequestData.getAppVersion());

        return ApiResult.resultWith(ApiResultCodeEnum.SUCCESS, mockResponse);
    }
}
