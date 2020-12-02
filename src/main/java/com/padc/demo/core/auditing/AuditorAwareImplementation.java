package com.padc.demo.core.auditing;

import com.padc.demo.core.security.Securitycontext;
import com.padc.demo.core.security.UserDetailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // if user not have logged in, name is AnonymousUser. Otherwise user's name
        //https://stackoverflow.com/questions/22678891/how-to-get-user-id-from-customuser-on-spring-security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return Optional.of(name);

    }
}
