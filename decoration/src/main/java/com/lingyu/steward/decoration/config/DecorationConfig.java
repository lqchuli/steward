package com.lingyu.steward.decoration.config;

import com.lingyu.steward.web.config.MvcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author allan
 * @date 12/11/2017
 */
@Configuration
@ComponentScan(basePackages = {
        "com.lingyu.steward.decoration"
})
@Import(MvcConfig.class)
public class DecorationConfig {
}
