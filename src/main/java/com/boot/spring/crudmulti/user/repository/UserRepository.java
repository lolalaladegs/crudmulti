package com.boot.spring.crudmulti.user.repository;

import com.boot.spring.crudmulti.user.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}