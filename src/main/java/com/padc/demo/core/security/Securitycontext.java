package com.padc.demo.core.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * This class returns UserDetailHandler, when
 * details about the user is needed in the system.
 */

@NoArgsConstructor
@Component
public class Securitycontext {

    /**
     * This method is used in Controller to get access to user details
     * @return UserDetailHandler
     */

    public UserDetailHandler getUserDetailHandler(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailHandler) authentication.getPrincipal();
    }

}
