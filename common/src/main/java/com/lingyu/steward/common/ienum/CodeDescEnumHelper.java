package com.lingyu.steward.common.ienum;

/**
 * 对于枚举的工具类
 *
 * @author allan
 */
public class CodeDescEnumHelper {

    /**
     * 根据枚举值得到枚举描述
     *
     * @param cls
     * @param code
     * @return
     */
    public static Object getEnumDescByValue(Class<? extends CodeDescCommonEnum> cls, Object code) {
        CodeDescCommonEnum ice = getEnumTypeByValue(cls, code);
        if (ice != null) {
            return ice.getDesc();
        }
        return null;
    }

    /**
     * 根据枚举值得到枚举类型
     *
     * @param cls
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends CodeDescCommonEnum> T getEnumTypeByValue(Class<T> cls, Object code) {
        if (code == null) {
            return null;
        }
        for (T item : cls.getEnumConstants()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
