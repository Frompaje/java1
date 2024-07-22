package com.buildrun.youtube.service.factory;

import com.buildrun.youtube.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class MakeCreateUser {
    public static User userFactory() {
        User user = new User(
                UUID.randomUUID(),
                "Jhon",
                "email@example.com",
                "123456",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return user;
    }
}
