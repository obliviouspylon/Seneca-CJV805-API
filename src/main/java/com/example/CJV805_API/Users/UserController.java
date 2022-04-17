package com.example.CJV805_API.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public Optional<User> getUser(@RequestParam("id") String id, @RequestParam("email") String email){
        return userService.getUser(id,email);
    }

    @PostMapping("/login")
    public User validateUser(@RequestBody User user){
        return userService.validateUser(user);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        if (userService.addUser(user)){
            return ("User registered!");
        } else {
            return ("Unable to Register User");
        }
    }
}
