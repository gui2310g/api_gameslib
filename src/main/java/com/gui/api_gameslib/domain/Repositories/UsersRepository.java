package com.gui.api_gameslib.domain.Repositories;

import com.gui.api_gameslib.domain.entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);
}
