package com.towFactAuth;


import com.towFactAuth.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by user on 22.05.16.
 */
public class TOTPUserDetails implements UserDetails{
    private String name;
    private String password;
    private boolean enabled;
    private String secret;
    private Collection authorities = new HashSet<>();


    public TOTPUserDetails(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        //this.enabled = user.isEnabled();
        this.secret = user.getSecret();
        //populateAuthorities(user.getRoles());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
