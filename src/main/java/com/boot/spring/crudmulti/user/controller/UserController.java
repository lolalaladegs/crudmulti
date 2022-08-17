package com.boot.spring.crudmulti.user.controller;


import com.boot.spring.crudmulti.user.dto.UserEntity;
import com.boot.spring.crudmulti.user.model.MessageResponse;
import com.boot.spring.crudmulti.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.boot.spring.crudmulti.user.constant.UserConstant.*;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        try{
            List<UserEntity> userList = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }catch (CannotCreateTransactionException transactionException){
            log.error(transactionException.getMessage(), transactionException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping(path = "/id/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> getUsersByIds(@RequestBody List<Integer> ids){
        try{
            List<UserEntity> users = userService.getUsersByIds(ids);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }catch (CannotCreateTransactionException transactionException){
            log.error(transactionException.getMessage(), transactionException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList());
        }
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> addUser(@RequestBody List<UserEntity> users){
        try{
            userService.addUser(users);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MessageResponse(SUCCESSFULLY_CREATE_USER));
        }catch (CannotCreateTransactionException transactionException){
            log.error(transactionException.getMessage(), transactionException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(CANNOT_CREATE_USER_INTERNAL_SERVER_ERROR));
        }catch (Exception exception){
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(CANNOT_CREATE_USER));
        }
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateUser(@RequestBody List<UserEntity> users){
        try{
            userService.updateUser(users);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(SUCCESSFULLY_UPDATE_USER));
        }catch (CannotCreateTransactionException transactionException){
            log.error(transactionException.getMessage(), transactionException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(CANNOT_UPDATE_USER_INTERNAL_SERVER_ERROR));
        }catch (Exception exception){
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(CANNOT_UPDATE_USER));
        }
    }

    @DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteUser(@RequestBody List<Integer> ids){
        try{
            userService.deleteUser(ids);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(SUCCESSFULLY_DELETE_USER));
        }catch (CannotCreateTransactionException transactionException){
            log.error(transactionException.getMessage(), transactionException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(CANNOT_DELETE_USER_INTERNAL_SERVER_ERROR));
        }catch (Exception exception){
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(CANNOT_DELETE_USER));
        }
    }

}
