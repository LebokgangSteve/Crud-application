package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;



@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Password hashing
//    public static String doHashing (String password)
//    {
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(password.getBytes());
//            byte[] resultByteArray= messageDigest.digest();
//
//            StringBuilder sb = new StringBuilder();
//            for (byte b: resultByteArray) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//            return "";
//    }




    //Add
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        //user.setPassword(doHashing(user.getPassword()));
        user.setPassword(user.getPassword());
       return "User successfully created \n"+ userService.createUser(user);

    }
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users){

        //users.setPassword(doHashing(users.getPassword()));
        return  userService.createUsers(users);
    }

    //GET
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return  userService.getUserById(id);

    }

    @GetMapping("/users")
    public  List<User> getAllUsers(){
        return  userService.getUsers();
    }


    //Update

    @PutMapping("/updateUser")
    public User update(User user){
        return userService.updateUser(user);
    }

    //Delete
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUserById(id);
    return "User deleted successfully";


}


}
