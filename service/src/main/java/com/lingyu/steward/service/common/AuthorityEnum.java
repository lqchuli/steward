package com.lingyu.steward.service.common;

import com.lingyu.steward.common.ienum.ValueDescCommonEnum;

/**
 * @author allan
 * @date 10/11/2017
 */
public enum AuthorityEnum implements ValueDescCommonEnum {
    MANAGER_ROOT("MANAGER_ROOT", "超级管理员");

    private String value;
    private String desc;

    AuthorityEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Object getDesc() {
        return null;
    }
}
