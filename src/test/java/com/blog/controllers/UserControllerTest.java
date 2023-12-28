package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;
import com.blog.util.ConstantsAndObjects;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ConstantsAndObjects constantsAndObjects;

    @Test
    public void testCreateUser() throws Exception {

        mockMvc
                .perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                    "    \"id\": \"1\",\n" +
                    "    \"name\": \"John Doe\",\n" +
                    "    \"email\": \"john.doe@example.com\",\n" +
                    "    \"password\": \"hashedPassword\",\n" +
                    "    \"about\": \"About John\"\n" +
                    "}")
                )
                .andExpect(status().isCreated());

        UserDto userDto = constantsAndObjects.userDto();

        when(userService.createUser(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
        verify(userService, times(1)).createUser(userDto);
    }

    @Test
    public void testUpdateUser() throws Exception {

        mockMvc
                .perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"John Doe\",\n" +
                                "    \"email\": \"john.doe@example.com\",\n" +
                                "    \"password\": \"hashedPassword\",\n" +
                                "    \"about\": \"About John\"\n" +
                                "}")
                )
                .andExpect(status().isOk());

//        UserDto userDto = constantsAndObjects.userDto();
//        Integer userId = 1;
//        when(userService.updateUser(userDto, userId)).thenReturn(userDto);
//
//        ResponseEntity<UserDto> responseEntity = userController.updateUser(userDto, userId);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(userDto, responseEntity.getBody());
//        verify(userService, times(1)).updateUser(userDto, userId);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<ApiResponse> responseEntity = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User deleted successfully", responseEntity.getBody().getMessage());
//        assertTrue(responseEntity.getBody().isSuccess());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> users = constantsAndObjects.userDtosList();
        when(userService.getAllUser()).thenReturn(users);

        ResponseEntity<List<UserDto>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
        verify(userService, times(1)).getAllUser();
    }

    @Test
    public void testGetUserById() {
        UserDto userDto = constantsAndObjects.userDto();
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.getUserById(userId);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
        verify(userService, times(1)).getUserById(userId);
    }
}
