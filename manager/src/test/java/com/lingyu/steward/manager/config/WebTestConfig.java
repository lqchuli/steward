package com.lingyu.steward.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by allan on 13/11/2017.
 */
@Configuration
@Import({
        ManagerConfig.class
})
public class WebTestConfig {
}
