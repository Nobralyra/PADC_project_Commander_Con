package com.padc.demo.core.security;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Securitycontext {

    public UserDetailHandler getUserDetailHandler(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailHandler) authentication.getPrincipal();
    }

}
