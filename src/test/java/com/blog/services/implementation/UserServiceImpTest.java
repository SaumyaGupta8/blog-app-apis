package com.blog.services.implementation;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImpTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImp userService;

    UserDto userDto = UserDto.builder()
            .id(1)
            .name("John Doe")
            .email("john.doe@example.com")
            .about("About John")
            .password("hashedPassword")  // This should be the hashed password
            .build();;

    // Mocking behavior for findById
    User user = User.builder()
            .id(1)
            .name("John Doe")
            .email("john.doe@example.com")
            .about("About John")
            .password("hashedPassword")  // This should be the hashed password
            .build();

    @InjectMocks
    private UserServiceImp userService;

    @BeforeAll
    static void beforeAll() {

        userDto = UserDto.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .about("About John")
                .password("hashedPassword")  // This should be the hashed password
                .build();

        user = User.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .about("About John")
                .password("hashedPassword")  // This should be the hashed password
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createUser_ShouldCreateUserSuccessfully() {
        // Arrange

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        // Act
        UserDto createdUserDto = userService.createUser(userDto);

        // Assert
        assertNotNull(createdUserDto);
        verify(userRepo, times(1)).save(user);
        assertEquals(createdUserDto, userDto);
    }

    @Test
    void updateUser_ShouldUpdateUserSuccessfully() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        user.setName("Mia Larry");
        user.setEmail("mia.larry@gmail.com");
        user.setPassword("mdbud");
        user.setAbout("About Mia");

        userDto.setName("Mia Larry");
        userDto.setEmail("mia.larry@gmail.com");
        userDto.setPassword("mdbud");
        userDto.setAbout("About Mia");

//        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        when(userRepo.save(user)).thenReturn(user);

        // Act
        UserDto updatedUserDto = userService.updateUser(userDto, userId);

        // Assert
        assertNotNull(updatedUserDto);
        assertEquals(updatedUserDto, userDto);
        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void updateUser_ShouldThrowResourceNotFoundExceptionWhenUserNotFound() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto, userId),
                "ResourceNotFoundException should be thrown for non-existent user");
    }

    @Test
    void getUserById_ShouldReturnUserDto() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        // Act
        UserDto retrievedUserDto = userService.getUserById(userId);

        // Assert
        assertNotNull(retrievedUserDto);
        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void getUserById_ShouldThrowResourceNotFoundExceptionWhenUserNotFound() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId),
                "ResourceNotFoundException should be thrown for non-existent user");
    }

    @Test
    void getAllUser_ShouldReturnListOfUserDto() {
        // Arrange
        List<User> users = Arrays.asList(
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
        List<UserDto> userDtos = Arrays.asList(UserDto.builder()
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

        when(userRepo.findAll()).thenReturn(users);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());

        // Act
        List<UserDto> retrievedUserDtos = userService.getAllUser();

        // Assert
        assertNotNull(retrievedUserDtos);
        assertEquals(users.size(), retrievedUserDtos.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void deleteUser_ShouldDeleteUserSuccessfully() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).delete(user);
    }

    @Test
    void deleteUser_ShouldThrowResourceNotFoundExceptionWhenUserNotFound() {
        // Arrange
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId),
                "ResourceNotFoundException should be thrown for non-existent user");
    }
}
