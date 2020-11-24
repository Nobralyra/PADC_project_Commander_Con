package com.padc.demo.user.service;

import com.padc.demo.core.IService;
import com.padc.demo.core.security.SecurityConfig;
import com.padc.demo.user.domain.Role;
import com.padc.demo.user.domain.User;
import com.padc.demo.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User>{

    private final IUserRepository iUserRepository;
    private final SecurityConfig securityConfig;

    public UserService(IUserRepository iUserRepository, SecurityConfig securityConfig) {
        this.iUserRepository = iUserRepository;
        this.securityConfig = securityConfig;
    }


    public void save(User user) {
        user.setPassword(securityConfig.getEncoder().encode(user.getPassword()));
        //participant as default (role 3)
        user.setRole(Role.ROLE_PARTICIPANT);
        iUserRepository.save(user);
    }


    public User findById(long id){
        Optional<User> user = iUserRepository.findById(id);
        /*The double colon operator :: is used to call a method/constructor
        by referrring to the class. Syntax: <<Class name>> :: <<method or constructor>>*/
        return user.orElseThrow(EntityNotFoundException::new);
    }


    public List<User> findAll() {
        List<User> listen = new ArrayList<>();
        for(User u: iUserRepository.findAll()){
            listen.add(u);
        }
        return listen;
    }


    public void deleteByID(long id) {
        iUserRepository.deleteById(id);
    }
}
