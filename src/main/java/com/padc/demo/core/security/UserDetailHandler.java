package com.padc.demo.core.security;

import com.padc.demo.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * This class appers as a middle class between the current User and Spring Security.
 * It contains information about the roles, the user has, and it has
 * an access to al information about the user.
 * It is the Securitycontext class, which gives an instance of this class,
 * when the information about the current user is needed for instance in Controller.
 */

public class UserDetailHandler implements UserDetails {

    private final User user;

    public UserDetailHandler(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
    public boolean isEnabled() { return true; }

    public User getUser() { return user; }
    public long getId(){ return this.user.getId(); }


}
