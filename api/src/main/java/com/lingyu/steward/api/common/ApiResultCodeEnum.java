package com.lingyu.steward.api.common;

/**
 * @author allan
 * @date 14/11/2017
 */
public enum ApiResultCodeEnum {
    SUCCESS(2000, "请求成功"),
    EMPTY_SIGN(3000, "签名参数未传递"),
    SIGN_ERROR(3001, "签名参数不正确"),
    SYSTEM_BAD_REQUEST(5000, "系统请求失败");

    private int resultCode;
    private String resultMsg;

    ApiResultCodeEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
