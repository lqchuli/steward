package com.lingyu.steward.common.ienum;

/**
 * 对于枚举的工具类
 */
public class ValueDescEnumHelper {

    /**
     * 根据枚举值得到枚举描述
     *
     * @param cls
     * @param value
     * @return
     */
    public static Object getEnumDescByValue(Class<? extends ValueDescCommonEnum> cls, Object value) {
        ValueDescCommonEnum ice = getEnumTypeByValue(cls, value);
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
    public static <T extends ValueDescCommonEnum> T getEnumTypeByValue(Class<T> cls, Object value) {
        if (value == null) {
            return null;
        }
        for (T item : cls.getEnumConstants()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
