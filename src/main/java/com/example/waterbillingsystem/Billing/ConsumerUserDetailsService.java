package com.example.waterbillingsystem.Billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ConsumerUserDetailsService implements UserDetailsService {
    @Autowired
    private ConsumerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return null;
    }
}
