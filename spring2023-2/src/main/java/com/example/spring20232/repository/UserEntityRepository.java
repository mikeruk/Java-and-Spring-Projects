package com.example.spring20232.repository;

import com.example.spring20232.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    //Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findUserEntityByEmail(String email);

    UserEntity findUserEntityById(Long id);



}
