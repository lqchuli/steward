package com.lingyu.steward.datacenter.common;

import com.lingyu.steward.common.ienum.CodeDescCommonEnum;

/**
 * 权限
 *
 * @author allan
 * @date 10/11/2017
 */
public enum AuthorityEnum implements CodeDescCommonEnum {
    MANAGER_ROOT("MANAGER_ROOT", "超级管理员"),
    DECORATION("DECORATION", "装修公司");

    private String code;
    private String desc;

    AuthorityEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
