package com.boot.spring.crudmulti.user.service;

import com.boot.spring.crudmulti.user.dto.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    List<UserEntity> getUsersbyPage(int page, int size);

    List<UserEntity> getUsersByIds(List<Integer> ids);

    void addUser(List<UserEntity> users);

    void updateUser(List<UserEntity> users);

    void deleteUser(List<Integer> users);
}
