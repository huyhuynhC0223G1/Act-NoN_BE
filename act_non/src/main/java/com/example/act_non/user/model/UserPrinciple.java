package com.example.act_non.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String username, String password,
                         Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(Users users) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles roles : users.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        }

        return new UserPrinciple(users.getUsername(),
                users.getPassword(),
                authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
