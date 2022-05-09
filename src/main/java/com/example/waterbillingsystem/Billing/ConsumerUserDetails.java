package com.example.waterbillingsystem.Billing;

import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ConsumerUserDetails implements UserDetails {
    

    private Consumer consumer;

    public ConsumerUserDetails(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Consumer> roles = consumer.getConsumer();

        return null;
    }


    public Integer getId () {
        return consumer.getId();
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
