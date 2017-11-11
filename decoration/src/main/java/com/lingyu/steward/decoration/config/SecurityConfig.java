package com.lingyu.steward.decoration.config;

import com.lingyu.steward.datacenter.common.AuthorityEnum;
import com.lingyu.steward.decoration.config.security.DecorationAuthenticationProcessingFilter;
import com.lingyu.steward.decoration.config.security.DecorationAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Spring security 配置
 *
 * @author allan
 * @date 10/11/2017
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGIN_PAGE = "/login";
    public static final String LOGIN_SUCCESS_URL = "/index";
    public static final String LOGOUT_SUCCESS_URL = "/";

    private static String[] STATIC_RESOURCE_PATH = {
            "/resource/**",
            "/code/verifyImage",
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(STATIC_RESOURCE_PATH);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 内置超级管理员
     *
     * @param auth
     * @throws Exception
     */
    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("administrator")
                .password("lingyu123!@#")
                .roles((String) AuthorityEnum.MANAGER_ROOT.getValue());


    }

    @Resource(name = "decorationService")
    private UserDetailsService userDetailsService;

    /**
     * 定义一个适用于本项目的权限校验过滤器
     *
     * @return {@link DecorationAuthenticationProcessingFilter}
     */
    private DecorationAuthenticationProcessingFilter decorationAuthenticationProcessingFilter() {
        //权限校验提供者

        DecorationAuthenticationProvider authenticationProvider = new DecorationAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        //权限校验管理器，当然可以包含多个提供者
        AuthenticationManager authenticationManager = new ProviderManager(
                Collections.singletonList(authenticationProvider)
        );
        DecorationAuthenticationProcessingFilter authenticationProcessingFilter = new DecorationAuthenticationProcessingFilter();
        authenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        authenticationProcessingFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(LOGIN_SUCCESS_URL));
        // TODO: 10/11/2017  校验失败处理器待添加
        authenticationProcessingFilter.setAuthenticationFailureHandler(null);

        return authenticationProcessingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilter(decorationAuthenticationProcessingFilter())
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }
}
