package com.lingyu.steward.api.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 签名
 *
 * @author allan
 * @date 14/11/2017
 */
public class SignBuilder {
    public static String build(Map<String, String> signMap, String prefix, String suffix) throws UnsupportedEncodingException {
        if (prefix == null) {
            prefix = "";
        }
        if (suffix == null) {
            suffix = "";
        }
        StringBuilder preSignStr = new StringBuilder(prefix);
        signMap.forEach((key, value) -> {
            if (!StringUtils.isEmpty(value)) {
                preSignStr.append(key.toLowerCase()).append(value);
            }
        });
        preSignStr.append(suffix);
        return DigestUtils.md5DigestAsHex(preSignStr.toString().getBytes("utf-8"));
    }
}
