package com.lingyu.steward.datacenter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据访问层配置
 *
 * @author allan
 * @date 09/11/2017
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.lingyu.steward.datacenter.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.lingyu.steward.datacenter")
@ImportResource({"classpath:persistence_config_dev.xml", "classpath:persistence_config_prod.xml"})
public class DataCenterConfig {

}
