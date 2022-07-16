package com.example.emailmanager.EmailManager.Service;

import com.example.emailmanager.EmailManager.Repository.UserRepository;
import com.example.emailmanager.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    public Long save(User user) {
        Optional<User> maybeUser = userRepository.findUserByUsername(user.getUsername());
        if(!maybeUser.isEmpty()){
            user.setId(maybeUser.get().getId());
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public User getByUsername(String username){
        return userRepository.findUserByUsername(username).get();
    }

    public User getByStatus(boolean status){
        return userRepository.findUserByEnable(status).get();
    }
}
