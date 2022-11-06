package com.example.techub.techubStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.techub.techubStore.model.UserClient;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserClient, Integer> {

    Optional<UserClient> findByLogin(String login);

    @Query(value = "SELECT id,login,userAdmin from UserClient")
    List<UserClient> findAllUsers();

}
