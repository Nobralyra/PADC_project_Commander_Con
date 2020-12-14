package com.padc.demo.core.security;

import com.padc.demo.user.domain.User;
import com.padc.demo.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    /**
     * Loads user from database by username and returns UserDetailHandler (which
     * implements UserDetails) with user as a parametre
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = iUserRepository.findByUsername(username);
        User useren;
        if(user.isPresent()) {useren = user.get();}
        else throw new UsernameNotFoundException("Brugernavn: " + username + " ikke fundet");

        return new UserDetailHandler(useren);
        }

}
