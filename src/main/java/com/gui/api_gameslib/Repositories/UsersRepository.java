package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, String> {

    Optional<Users> findById(Integer id);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

}
