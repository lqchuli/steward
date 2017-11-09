package com.lingyu.steward.manager.config.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author allan
 * @date 10/11/2017
 */
public class StewardAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {
    private static final String ROLE_KEY = "role";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
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
            int role = Integer.parseInt(request.getParameter(ROLE_KEY));

            username = username.trim();
            StewardAuthenticationToken authRequest = new StewardAuthenticationToken(username, password, role);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


}
