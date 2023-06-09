package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //Post
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
     return userRepository.save(user);
    }


    @Override
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }
    //Get
    @Override
    public User getUserById(int id) {
        //String msg="User not found";
       return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    //Update user
    @Override
    public User updateUser(User user) {
        User oldUser = null;
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        } else {
            return new User();
        }
        //return "User updated successful";
        return oldUser;
    }
//Delete
    @Override
    public String deleteUserById(int id) {
        userRepository.deleteById(id);

     return "User deleted sucessfully";
    }
}
