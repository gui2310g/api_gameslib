package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Users;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.UserAuthenticated;
import com.gui.api_gameslib.exceptions.UserException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    @Transactional
    public Users CreateUser(Users users) throws UserException {
        if (usersRepository.findByEmail(users.getEmail()).isPresent())
            throw new UserException("Error: Email already exists");

        if(usersRepository.findByUsername(users.getUsername()).isPresent())
            throw new UserException("Error: Someone use this email");

        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return usersRepository.save(users);
    }

    public Iterable<Users> FindAllUsers() throws UserException {
        Iterable<Users> users = usersRepository.findAll();
        if(!users.iterator().hasNext()) throw new UserException("There is no registered Users");
        return usersRepository.findAll();
    }

    public Users FindUserById(Integer id) throws UserException {
        return usersRepository.findById(id).orElseThrow(
                () -> new UserException("Can't found the user with this id")
        );
    }

    public Users updateUser(Users users, Integer id) throws UserException {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new UserException("Can't found the user with this id to update"));

        if (users.getUsername() != null) existingUser.setUsername(users.getUsername());
        if (users.getEmail() != null) existingUser.setEmail(users.getEmail());
        if (users.getPassword() != null)  existingUser.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(existingUser);
    }

    public Users DeleteUser(Integer id) throws UserException {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new UserException("Can't find the user with this ID to delete"));

        usersRepository.delete(existingUser);

        return existingUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository
                .findByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
