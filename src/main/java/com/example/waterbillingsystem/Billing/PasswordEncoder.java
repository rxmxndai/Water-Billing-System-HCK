package com.example.waterbillingsystem.Billing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[]args){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String rawPassword="rojan";
        String encodePassword=encoder.encode(rawPassword);
        System.out.println(encodePassword);
    }
}
