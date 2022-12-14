package com.boot.spring.crudmulti.user.service.implementation;

import com.boot.spring.crudmulti.user.dto.UserEntity;
import com.boot.spring.crudmulti.user.repository.UserRepository;
import com.boot.spring.crudmulti.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public List<UserEntity> getUsersbyPage(int page, int size) {
        Pageable paging = PageRequest.of(page,size, Sort.by("id").descending());
        List<UserEntity> userList = userRepository.findAll(paging).getContent();
        return userList;
    }

    @Override
    public List<UserEntity> getUsersByIds(List<Integer> ids) {
        List<UserEntity> userList = userRepository.findAllById(ids);
        return userList;
    }

    @Override
    public void addUser(List<UserEntity> users) {
        userRepository.saveAllAndFlush(users);
    }

    @Override
    public void updateUser(List<UserEntity> users) {
        userRepository.saveAllAndFlush(users);
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        userRepository.deleteAllById(ids);
    }
}
