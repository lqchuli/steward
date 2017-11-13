package com.lingyu.steward.manager.config;

import com.lingyu.steward.service.config.ServiceConfig;
import com.lingyu.steward.web.config.MvcConfig;
import com.lingyu.steward.web.service.config.WebServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author allan
 * @date 12/11/2017
 */
@Configuration
@ComponentScan(basePackages = {
        "com.lingyu.steward.manager"
})
@Import({
        MvcConfig.class,
        WebServiceConfig.class,
        ServiceConfig.class
})
public class ManagerConfig {
}
