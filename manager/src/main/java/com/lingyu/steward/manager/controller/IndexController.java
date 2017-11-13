package com.lingyu.steward.manager.controller;

import com.lingyu.steward.web.config.security.SecurityConfig;
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
}
