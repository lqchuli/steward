package com.lingyu.steward.web.service.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author allan
 * @date 13/11/2017
 */
@Controller
@ControllerAdvice
public class StewardExceptionHandler {
    private static final Log log = LogFactory.getLog(StewardExceptionHandler.class);

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult runtimeExceptionHandler(Exception ex) {
        log.error(ex);
        log.info("exception:" + ex.getMessage());
        return ApiResult.resultWith(ResultCodeEnum.SYSTEM_BAD_REQUEST, ex.getMessage(), null);
    }
}
