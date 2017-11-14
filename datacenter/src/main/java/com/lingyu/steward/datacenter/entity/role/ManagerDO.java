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
 * 装修管家运营中台
 *
 * @author allan
 * @date 11/11/2017
 */
@Entity
@Table(name = "Steward_Manager")
@Setter
@Getter
@Cacheable(false)
public class ManagerDO implements UserDetails, Serializable {
    private static final long serialVersionUID = -3850670361792392711L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Manager_Id")
    private Integer managerId;
    /**
     * 登录账户
     */
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    /**
     * 管理员角色名称
     */
    private String roleName;
    /**
     * 管理员姓名
     */
    @Column(name = "Name")
    private String name;

    /**
     * 管理员权限
     */
    @Lob
    @Column(name = "Authorities")
    private Set<AuthorityEnum> authorities;

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
        authorities.forEach(authority -> grantedAuthorities
                .add(new SimpleGrantedAuthority("ROLE_" + authority.getCode())));
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
