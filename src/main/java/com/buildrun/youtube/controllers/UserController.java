package com.buildrun.youtube.controllers;


import com.buildrun.youtube.controllers.Dto.user.CreateAccountDto;
import com.buildrun.youtube.controllers.Dto.user.CreateUserDto;
import com.buildrun.youtube.controllers.Dto.user.UpdatedUserPasswordDto;
import com.buildrun.youtube.entity.User;
import com.buildrun.youtube.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUsers = userService.getAllUser();

        return ResponseEntity.ok(listUsers);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto body) {
        User user = userService.createUser(body);

        return ResponseEntity.created(URI.create("/users" + user)).build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<User> createaAccount(@PathVariable("userId") String id, @RequestBody CreateAccountDto createAccountDto) {
        userService.createrAccount(id, createAccountDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String id, @RequestBody UpdatedUserPasswordDto passsword) {
        User user = userService.updatedUserById(id, passsword);

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
