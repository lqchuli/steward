package com.lingyu.steward.api.base;

import com.lingyu.steward.api.common.ApiResult;
import com.lingyu.steward.api.common.ApiResultCodeEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Api统一的异常处理
 * Created by allan on 14/11/2017.
 */
@Controller
@ControllerAdvice
public class ApiExceptionHandler {
    private static final Log log = LogFactory.getLog(ApiExceptionHandler.class);

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult runtimeExceptionHandler(Exception ex) {
        log.error(ex);
        log.info("exception:" + ex.getMessage());
        return ApiResult.errorWith(ApiResultCodeEnum.SYSTEM_BAD_REQUEST, ex.getMessage());
    }
}
