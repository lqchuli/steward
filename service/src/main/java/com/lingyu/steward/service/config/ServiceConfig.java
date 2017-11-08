package com.lingyu.steward.service.config;

import com.lingyu.steward.datacenter.config.DataCenterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author allan
 * @date 09/11/2017
 */
@Configuration
@ComponentScan(basePackages = {
        "com.lingyu.steward.service.core"
})
@Import(DataCenterConfig.class)
public class ServiceConfig {
}
