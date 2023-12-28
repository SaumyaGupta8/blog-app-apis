package com.blog.util;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConstantsAndObjects {

    public UserDto userDto() {

        return UserDto.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .about("About John")
                .password("hashedPassword")  // This should be the hashed password
                .build();
    }

    public User user() {

        return User.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .about("About John")
                .password("hashedPassword")  // This should be the hashed password
                .build();
    }

    public List<UserDto> userDtosList() {

        return Arrays.asList(
                UserDto.builder()
                        .id(1)
                        .name("John Doe")
                        .email("john.doe@example.com")
                        .about("About John")
                        .password("hashedPassword")  // This should be the hashed password
                        .build(),
                UserDto.builder()
                        .id(1)
                        .name("Lisa Ann")
                        .email("lisa.ann@example.com")
                        .about("About Lisa")
                        .password("hashedPassword")  // This should be the hashed password
                        .build());
    }

    public List<User> usersList() {

        return Arrays.asList(
                User.builder()
                        .id(1)
                        .name("John Doe")
                        .email("john.doe@example.com")
                        .about("About John")
                        .password("hashedPassword")  // This should be the hashed password
                        .build(),
                User.builder()
                        .id(1)
                        .name("Lisa Ann")
                        .email("lisa.ann@example.com")
                        .about("About Lisa")
                        .password("hashedPassword")  // This should be the hashed password
                        .build());
    }
}
