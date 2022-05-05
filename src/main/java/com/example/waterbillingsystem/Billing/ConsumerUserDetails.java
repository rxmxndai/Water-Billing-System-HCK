package com.example.waterbillingsystem.Billing;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ConsumerUserDetails implements UserDetails {
    private Consumer consumer;

    public ConsumerUserDetails(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return consumer.getPassword();
    }

    @Override
    public String getUsername() {
        return consumer.getName();
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
