package com.lingyu.steward.manager.controller;

import com.lingyu.steward.web.config.security.SecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author allan
 * @date 11/11/2017
 */
@Controller
public class IndexController {
    @RequestMapping(value = SecurityConfig.LOGIN_URL, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = SecurityConfig.LOGIN_SUCCESS_URL)
    @PreAuthorize("hasRole('MANAGER_ROOT')")
    public String index() {
        return "index";
    }

    @RequestMapping(value = SecurityConfig.LOGIN_FAILED_URL)
    public String loginFailed(HttpServletRequest request, RedirectAttributes attributes) {
        attributes.addFlashAttribute("errMsg", request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));
        attributes.addFlashAttribute("failedFlag", true);
        return "redirect:login";
    }
}
