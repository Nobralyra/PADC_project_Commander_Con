package com.padc.demo.user.service;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.user.domain.User;
import com.padc.demo.user.repository.IUserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    private final IUserRepository iUserRepository;
    private final Encoder encoder;

    public UserService(IUserRepository iUserRepository, Encoder encoder) {
        this.iUserRepository = iUserRepository;
        this.encoder = encoder;
    }

    @Override
    public void save(User user) {

        user.setPassword(encoder.getEncoder().encode(user.getPassword()));
        iUserRepository.save(user);
    }

    @Override
    public User findById(long id){
        Optional<User> user = iUserRepository.findById(id);

        return user.orElseThrow(EntityNotFoundException::new);
    }

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
