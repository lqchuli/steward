package com.lingyu.steward.web.service.controller;

import com.lingyu.steward.web.service.resource.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author allan
 * @date 12/11/2017
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private StaticResourceService resourceService;


}
