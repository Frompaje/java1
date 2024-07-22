package com.buildrun.youtube.service;

import com.buildrun.youtube.controllers.Dto.user.CreateAccountDto;
import com.buildrun.youtube.controllers.Dto.user.CreateUserDto;
import com.buildrun.youtube.controllers.Dto.user.UpdatedUserPasswordDto;
import com.buildrun.youtube.entity.Account;
import com.buildrun.youtube.entity.BillinAddress;
import com.buildrun.youtube.entity.User;
import com.buildrun.youtube.exception.UserAlreadyExistExeception;
import com.buildrun.youtube.exception.UserNotFoundExeception;
import com.buildrun.youtube.repository.AccountRepositoy;
import com.buildrun.youtube.repository.BillingAnddressRepository;
import com.buildrun.youtube.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepositoy accountRepositoy;
    private BillingAnddressRepository billingAnddressRepository;

    public UserService(UserRepository userRepository, AccountRepositoy accountRepositoy, BillingAnddressRepository billingAnddressRepository) {
        this.userRepository = userRepository;
        this.accountRepositoy = accountRepositoy;
        this.billingAnddressRepository = billingAnddressRepository;
    }

    public User createUser(CreateUserDto user) {
        Optional<User> userExist = userRepository.getByEmail(user.email());

        if (userExist.isPresent()) {
            throw new UserAlreadyExistExeception("User already exists");
        }
        User userCreated = new User(
                UUID.randomUUID(),
                user.username(),
                user.email(),
                user.password(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return userRepository.save(userCreated);
    }

    public Optional<User> getUserById(String id) {
        UUID userId = UUID.fromString(id);
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundExeception("User not found");
        }

        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteById(String id) {
        UUID userId = UUID.fromString(id);
        Optional<User> isUserExist = userRepository.findById(userId);

        if (isUserExist.isEmpty()) {
            throw new UserNotFoundExeception("User not found");
        }

        userRepository.deleteById(userId);
    }

    public User updatedUserById(String id, UpdatedUserPasswordDto passwordDto) {
        UUID userId = UUID.fromString(id);
        Optional<User> isUserExist = userRepository.findById(userId);

        if (isUserExist.isEmpty()) {
            throw new UserNotFoundExeception("User not found");
        }

        User user = isUserExist.get();

        user.setPassword(passwordDto.password());

        return userRepository.save(user);
    }

    public void createrAccount(String id, CreateAccountDto createAccountDto) {
        UUID userId = UUID.fromString(id);

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundExeception("User not found");
        }

        Account account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );

        Account accountCreated = accountRepositoy.save(account);

        BillinAddress billinAddress = new BillinAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAnddressRepository.save(billinAddress);

    }
}
