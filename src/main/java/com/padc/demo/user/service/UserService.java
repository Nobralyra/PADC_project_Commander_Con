package com.padc.demo.user.service;

import com.padc.demo.core.IService;
import com.padc.demo.user.domain.User;
import com.padc.demo.user.repository.IUserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) throws NotFoundException {
        try{
            return iUserRepository.findById(id);
        }catch (IllegalArgumentException ia)
        {
            throw new NotFoundException("Not found");
        }
    }

        /*try{
            return iUserRepository.findById(id);
        }catch (IllegalArgumentException ia){
            System.out.println(ia);
            return Optional.empty();
        }*/


    @Override
    public List<User> findAll() {
        List<User> listen = new ArrayList<>();
        for(User u: iUserRepository.findAll()){
            listen.add(u);
        }
        return listen;
    }

    @Override
    public void deleteByID(long id) {
        iUserRepository.deleteById(id);
    }
}
