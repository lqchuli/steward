package com.lingyu.steward.api.config;

import com.lingyu.steward.service.config.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by allan on 14/11/2017.
 */
@Configuration
@Import({
        ApiWebConfig.class,
        ServiceConfig.class
})
public class ApiTestConfig {
}
