package com.lingyu.steward.api.controller.app;

import com.lingyu.steward.api.base.AppApiTestBase;
import com.lingyu.steward.api.common.ApiConstant;
import com.lingyu.steward.api.common.ApiResultCodeEnum;
import com.lingyu.steward.api.utils.SignBuilder;
import com.lingyu.steward.common.utils.StringUtilsExt;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by allan on 14/11/2017.
 */
public class DemoControllerTest extends AppApiTestBase {
    @Test
    public void getDemo() throws Exception {
        //签名通过成功返回数据
        long timestamp = System.currentTimeMillis();
        Map<String, String> signMap = new TreeMap<>();
        signMap.put("timestamp", String.valueOf(timestamp));
        String requestSign = SignBuilder.build(signMap, ApiConstant.SECRET_KEY, ApiConstant.SECRET_KEY);
        mockMvc.perform(post("/demo/getDemo")
                .headers(httpHeaders)
                .param("timestamp", String.valueOf(timestamp))
                .param("sign", requestSign))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(ApiResultCodeEnum.SUCCESS.getResultCode()))
                .andExpect(jsonPath("$.data.appVersion").value(mockSysData.getAppVersion()));
        //签名未通过
        requestSign = UUID.randomUUID().toString();
        mockMvc.perform(post("/demo/getDemo")
                .headers(httpHeaders)
                .param("timestamp", String.valueOf(timestamp))
                .param("sign", requestSign))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(ApiResultCodeEnum.SIGN_ERROR.getResultCode()));
    }
}