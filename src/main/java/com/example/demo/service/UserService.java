package com.example.demo.service;


import com.example.demo.entity.User;


import java.util.List;



public interface UserService {

    User createUser(User user);
    List<User> createUsers(List<User> users);
    User getUserById(int id);
    List<User> getUsers();
    User updateUser(User user);
    String deleteUserById(int id);


}

