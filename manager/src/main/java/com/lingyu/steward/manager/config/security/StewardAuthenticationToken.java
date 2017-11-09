package com.lingyu.steward.manager.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 角色token
 *
 * @author allan
 * @date 10/11/2017
 */
@Setter
@Getter
public class StewardAuthenticationToken extends UsernamePasswordAuthenticationToken {
    /**
     * 登录角色
     */
    private int role;

    public StewardAuthenticationToken(Object principal, Object credentials, int role) {
        super(principal, credentials);
        this.role = role;
    }

    public StewardAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, int role) {
        super(principal, credentials, authorities);
        this.role = role;
    }
}
