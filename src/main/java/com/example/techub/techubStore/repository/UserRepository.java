package com.example.techub.techubStore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techub.techubStore.model.UserClient;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserClient, Integer> {

    Optional<UserClient> findByLogin(String login);

}
