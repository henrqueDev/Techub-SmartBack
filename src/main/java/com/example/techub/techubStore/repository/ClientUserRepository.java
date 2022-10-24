package com.example.techub.techubStore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techub.techubStore.model.ClientUser;

import java.util.Optional;

public interface ClientUserRepository extends JpaRepository<ClientUser, Integer> {

    Optional<ClientUser> findByLogin(String login);
}
