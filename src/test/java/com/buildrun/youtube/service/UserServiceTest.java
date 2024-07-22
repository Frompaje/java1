package com.buildrun.youtube.service;

import com.buildrun.youtube.controllers.Dto.user.CreateUserDto;
import com.buildrun.youtube.controllers.Dto.user.UpdatedUserPasswordDto;
import com.buildrun.youtube.entity.User;
import com.buildrun.youtube.exception.UserAlreadyExistExeception;
import com.buildrun.youtube.exception.UserNotFoundExeception;
import com.buildrun.youtube.repository.UserRepository;
import com.buildrun.youtube.service.factory.MakeCreateUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;


    @Nested
    class createUser {
        @Test

        @DisplayName("[Success] Should create a user")
        void shouldCreateUser() {
            User user = MakeCreateUser.userFactory();

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var sut = new CreateUserDto("Jhon", "email@example.com", "123456");
            User output = userService.createUser(sut);

            assertNotNull(output);

            User userCaptured = userArgumentCaptor.getValue();
            assertEquals(sut.username(), userCaptured.getUsername());
        }

        @Test
        @DisplayName("[Error] Should throw exception when user already exist")
        void shouldThrowExceptionWhenUserAlreadyExist() {
            User user = MakeCreateUser.userFactory();

            doReturn(Optional.of(user)).when(userRepository).getByEmail(user.getEmail());
            var input = new CreateUserDto("Jhon", "email@example.com", "123456");

            assertThrows(UserAlreadyExistExeception.class, () -> userService.createUser(input));
        }
    }

    @Nested
    class getUserByID {
        @Test
        @DisplayName("[Success] Should get user by id with success when optinal is present")
        void shouldGetUserByIdWithSuccess() {
            User user = MakeCreateUser.userFactory();

            doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
            var sut = userService.getUserById(user.getUserId().toString());
            var userValue = sut.get();

            assertTrue(sut.isPresent());
            assertEquals(user.getUserId(), userValue.getUserId());
        }

        @Test
        @DisplayName("[Error] Should get user by id with error when optional is empty")
        void errorWhenOptionalIsEmpty() {
            User user = MakeCreateUser.userFactory();

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());
            assertThrows(UserNotFoundExeception.class, () -> userService.getUserById(user.getUserId().toString()));
        }
    }

    @Nested
    class listUsers {

        @Test
        @DisplayName("[Success] Should return all users with sucess")
        void shoulReturnAllusersWithSuccess() {
            User user = MakeCreateUser.userFactory();
            List<User> userList = List.of(user);

            doReturn(List.of(userList)).when(userRepository).findAll();
            List<User> sut = userService.getAllUser();

            assertNotNull(sut);
            assertEquals(userList.size(), sut.size());
        }
    }

    @Nested
    class deletedById {
        @Test
        @DisplayName("[Success] Should delete user with id")
        void shouldDeleteUserById() {
            User user = MakeCreateUser.userFactory();

            doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
            doNothing().when(userRepository).deleteById(user.getUserId());
            userService.deleteById(user.getUserId().toString());

            Mockito.verify(userRepository, Mockito.times(1)).deleteById(user.getUserId());
        }

        @Test
        @DisplayName("[Error] Should delete user with id")
        void errorWhenOptionalIsEmpty() {
            User user = MakeCreateUser.userFactory();

            doReturn(Optional.empty()).when(userRepository).findById(user.getUserId());

            assertThrows(UserNotFoundExeception.class, () -> userService.deleteById(user.getUserId().toString()));
        }

    }

    @Nested
    class updatedUserById {
        @Test
        @DisplayName("[Success] Should updated user password")
        void sholdUpdatePassword() {
            User user = MakeCreateUser.userFactory();
            var updatedUserPasswordDto = new UpdatedUserPasswordDto("updated-password");

            doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
            doReturn(user).when(userRepository).save(user);
            User updatedUserPassword = userService.updatedUserById(user.getUserId().toString(), updatedUserPasswordDto);

            Mockito.verify(userRepository, Mockito.times(1)).findById(user.getUserId());
            assertEquals(user.getPassword(), updatedUserPassword.getPassword());
        }

        @Test
        @DisplayName("[Error] shouldn't update the user's password, because it doesn't exist")
        void errorWhenOptionalIsEmpty() {
            User user = MakeCreateUser.userFactory();
            var updatedUserPasswordDto = new UpdatedUserPasswordDto("updated-password");

            doReturn(Optional.empty()).when(userRepository).findById(user.getUserId());

            assertThrows(UserNotFoundExeception.class, () -> userService.updatedUserById(user.getUserId().toString(), updatedUserPasswordDto));

        }

    }

}