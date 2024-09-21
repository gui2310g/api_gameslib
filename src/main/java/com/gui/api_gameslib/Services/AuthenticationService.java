package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.entities.Users;
import com.gui.api_gameslib.exceptions.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) throws AuthException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Invalid email"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new AuthException("Invalid password");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), password);

        return jwtService.generateToken(authentication);
    }
}
