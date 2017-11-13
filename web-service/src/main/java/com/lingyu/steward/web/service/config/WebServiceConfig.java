package com.lingyu.steward.web.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author allan
 * @date 13/11/2017
 */
@Configuration
@ComponentScan(basePackages = {
        "com.lingyu.steward.web.service"
})
public class WebServiceConfig {
}
