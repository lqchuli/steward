package com.lingyu.steward.api.common;

import lombok.Getter;
import lombok.Setter;

/**
 * APP接口需要的一些系统级参数
 *
 * @author allan
 * @date 14/11/2017
 */
@Setter
@Getter
public class AppSysRequestData {
    /**
     * app版本号
     */
    private String appVersion;
    // TODO: 14/11/2017 其他系统级参数

    public static final String APP_VERSION_KEY = "appVersion";
}
