package com.lingyu.steward.web.config.security;

import com.lingyu.steward.datacenter.common.AuthorityEnum;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author allan
 * @date 10/11/2017
 */
public class StewardAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {
    private static final String MANAGER_ROOT_DEFAULT_USERNAME = "administrator";
    private static final String MANAGER_ROOT_DEFAULT_PASSWORD = "lingyu123!@#";
    private static final String METHOD = "POST";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = request.getParameter(getUsernameParameter());
            String password = request.getParameter(getPasswordParameter());
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }
            if (MANAGER_ROOT_DEFAULT_USERNAME.equals(username) && MANAGER_ROOT_DEFAULT_PASSWORD.equals(password)) {
                Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + AuthorityEnum.MANAGER_ROOT.getCode()));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        grantedAuthorities
                );
                return usernamePasswordAuthenticationToken;
            }

            // TODO: 11/11/2017 图片验证码
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


}
