package com.lingyu.steward.datacenter.entity.role;

import com.lingyu.steward.datacenter.common.AuthorityEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 装修公司登录帐号
 *
 * @author allan
 * @date 11/11/2017
 */
@Entity
@Table(name = "Steward_Decoration")
@Setter
@Getter
@Cacheable(false)
public class DecorationDO implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Decoration_Id")
    private Integer decorationId;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    /**
     * 是否可用（删除）
     */
    private boolean enabled;

    /**
     * 是否锁定
     * true表示未锁定，可用
     * false表示已锁定
     */
    private boolean accountNonLocked;

    // TODO: 11/11/2017  另外的一些字段

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + AuthorityEnum.DECORATION.getValue()));

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
