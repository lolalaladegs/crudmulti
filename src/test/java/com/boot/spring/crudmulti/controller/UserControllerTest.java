package com.boot.spring.crudmulti.controller;

import com.boot.spring.crudmulti.user.controller.UserController;
import com.boot.spring.crudmulti.user.dto.UserEntity;
import com.boot.spring.crudmulti.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsersTest_whenSuccessful_thenReturn200OKStatus() throws  Exception {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());

        given(userService.getAllUsers()).willReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersTest_whenThereIsNoRetrievedUsers_thenReturn200OKStatus() throws  Exception {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());

        given(userService.getAllUsers()).willReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersTest_whenCaughtCannotCreateTransactionException_thenReturn500InternalServerErrorStatus() throws  Exception {
        CannotCreateTransactionException exception =
                new CannotCreateTransactionException("Something went wrong");

        given(userService.getAllUsers()).willThrow(exception);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getAllUsersTest_whenCaughtException_thenReturn400BadRequestStatus() throws  Exception {
        RuntimeException exception = new RuntimeException("Something went wrong");

        given(userService.getAllUsers()).willThrow(exception);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    void getUsersByIdsTest_whenSuccessful_thenReturn200OKStatus() throws  Exception {
//        List<UserEntity> userList = new ArrayList<>();
//        List<Integer> ids = Arrays.asList(1,2);
//
//        userList.add(new UserEntity());
//
//        given(userService.getUsersByIds(ids)).willReturn(userList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/id/")
//                        .content(ids.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getUserByIdTest_whenThereIsNoRetrievedUser_thenReturn200OKStatus() throws  Exception {
//        UserEntity user = new UserEntity();
//
//        given(userService.getUserById(1)).willReturn(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getUserByIdTest_whenCaughtCannotCreateTransactionException_thenReturn500InternalServerErrorStatus() throws  Exception {
//        CannotCreateTransactionException exception =
//                new CannotCreateTransactionException("Something went wrong");
//
//        given(userService.getUserById(1)).willThrow(exception);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    void getUserByIdTest_whenCaughtException_thenReturn400BadRequestStatus() throws  Exception {
//        RuntimeException exception = new RuntimeException("Something went wrong");
//
//        given(userService.getUserById(1)).willThrow(exception);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
    @Test
    void addUserTest_whenSuccessful_thenReturn201CreatedStatus() throws  Exception {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());

        willDoNothing().given(userService).addUser(userList);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(userList);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

//    @Test
//    void addUserTest_whenCaughtCannotCreateTransactionException_thenReturn500InternalServerErrorStatus() throws  Exception {
//        CannotCreateTransactionException exception =
//                new CannotCreateTransactionException("Something went wrong");
//
//        UserEntity user = new UserEntity();
//
//        willThrow(exception).willDoNothing().given(userService).addUser(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    void addUserTest_whenCaughtException_thenReturn400BadRequestStatus() throws  Exception {
//        RuntimeException exception = new RuntimeException("Something went wrong");
//
//        UserEntity user = new UserEntity();
//
//        willThrow(exception).willDoNothing().given(userService).addUser(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void updateUserTest_whenSuccessful_thenReturn200OKStatus() throws  Exception {
//        UserEntity user = new UserEntity();
//
//        willDoNothing().given(userService).updateUser(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/user/")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updateUserTest_whenCaughtCannotCreateTransactionException_thenReturn500InternalServerErrorStatus() throws  Exception {
//        CannotCreateTransactionException exception =
//                new CannotCreateTransactionException("Something went wrong");
//
//        UserEntity user = new UserEntity();
//
//        willThrow(exception).willDoNothing().given(userService).updateUser(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/user/")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    void updateUserTest_whenCaughtException_thenReturn400BadRequestStatus() throws  Exception {
//        RuntimeException exception = new RuntimeException("Something went wrong");
//
//        UserEntity user = new UserEntity();
//
//        willThrow(exception).willDoNothing().given(userService).updateUser(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/user/")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void deleteUserTest_whenSuccessful_thenReturn200OKStatus() throws  Exception {
//        int id = 1;
//
//        willDoNothing().given(userService).deleteUser(id);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteUserTest_whenCaughtCannotCreateTransactionException_thenReturn500InternalServerErrorStatus() throws  Exception {
//        CannotCreateTransactionException exception =
//                new CannotCreateTransactionException("Something went wrong");
//
//        int id = 1;
//
//        willThrow(exception).willDoNothing().given(userService).deleteUser(id);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    void deleteUserTest_whenCaughtException_thenReturn400BadRequestStatus() throws  Exception {
//        RuntimeException exception = new RuntimeException("Something went wrong");
//
//        int id = 1;
//
//        willThrow(exception).willDoNothing().given(userService).deleteUser(id);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
}
