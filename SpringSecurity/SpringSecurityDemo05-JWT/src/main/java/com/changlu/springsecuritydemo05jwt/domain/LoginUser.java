package com.changlu.springsecuritydemo05jwt.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.changlu.springsecuritydemo05jwt.domain.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LoginUser
 * @Author ChangLu
 * @Date 3/25/2022 2:23 PM
 * @Description 登录用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    @JSONField(serialize = false)
    private Set<GrantedAuthority> authorities;

    public LoginUser(User user, List<String> roles) {
        this.user = user;
        this.permissions = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = new HashSet<>();
        permissions.stream().forEach((roleName)->{
            authorities.add(new SimpleGrantedAuthority(roleName));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}