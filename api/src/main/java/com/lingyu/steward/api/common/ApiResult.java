package com.lingyu.steward.api.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allan
 * @date 14/11/2017
 */
@Setter
@Getter
public class ApiResult {
    private int resultCode;
    private String resultMsg;
    private Object data;

    public static ApiResult resultWith(ApiResultCodeEnum resultCode, Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        apiResult.data = data;
        return apiResult;
    }

    public static ApiResult resultWith(ApiResultCodeEnum resultCode) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        return apiResult;
    }

    public static ApiResult resultWith(ApiResultCodeEnum resultCode, String msg, Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = msg;
        apiResult.data = data;
        return apiResult;
    }

    public static ApiResult errorWith(ApiResultCodeEnum resultCode) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        return apiResult;
    }

    public static ApiResult errorWith(ApiResultCodeEnum resultCode, String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = msg;
        return apiResult;
    }
}
