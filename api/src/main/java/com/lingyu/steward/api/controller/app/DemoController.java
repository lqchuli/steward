package com.lingyu.steward.api.controller.app;

import com.lingyu.steward.api.common.ApiResult;
import com.lingyu.steward.api.common.AppSysRequestData;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 一个测试接口，注意RequestMapping等注解，
 * 无法继承实现，需要在实现类中添加，
 * 这里只是最为一个接口的路由说明
 *
 * @author allan
 * @date 14/11/2017
 */
@RequestMapping(value = "/demo", method = RequestMethod.POST)
public interface DemoController {
    /**
     * 接口说明
     *
     * @return
     */
    @RequestMapping("/getDemo")
    ApiResult getDemo(@RequestAttribute AppSysRequestData sysRequestData);
}
