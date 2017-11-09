package com.lingyu.steward.manager.config.security;

import lombok.Setter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by allan on 10/11/2017.
 */
@Setter
public class StewardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    /**
     * 装修管家运营中台
     */
    private UserDetailsService managerUserDetailsService;
    /**
     * 装修公司中台
     */
    private UserDetailsService decorationUserDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authenticationToken) throws AuthenticationException {
        //密码校验
        String presentedPassword = userDetails.getPassword();
        if (!passwordEncoder.matches((String) authenticationToken.getCredentials(), presentedPassword)) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        StewardAuthenticationToken authenticationToken = (StewardAuthenticationToken) usernamePasswordAuthenticationToken;
        UserDetails userDetails;
        try {
            userDetails = userDetailsService(authenticationToken.getRole()).loadUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("不存在该帐号", e);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("不存在该帐号");
        }
        return userDetails;
    }

    private UserDetailsService userDetailsService(int role) {
        // TODO: 10/11/2017 根据role返回不同的service
        return null;
    }
}
