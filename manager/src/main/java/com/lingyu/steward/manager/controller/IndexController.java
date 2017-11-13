package com.lingyu.steward.manager.controller;

import com.lingyu.steward.datacenter.common.AuthorityEnum;
import com.lingyu.steward.web.config.security.SecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author allan
 * @date 11/11/2017
 */
@Controller
public class IndexController {
    @RequestMapping(value = SecurityConfig.LOGIN_PAGE, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = SecurityConfig.LOGIN_SUCCESS_URL)
    @PreAuthorize("hasRole('MANAGER_ROOT')")
    public String index() {
        return "index";
    }
}
