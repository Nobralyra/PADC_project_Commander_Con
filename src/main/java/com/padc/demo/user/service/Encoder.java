package com.padc.demo.user.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

//https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
@Component
public class Encoder {

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
