package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.exception.DataAlreadyExistException;
import com.edts.edtstechnicaltest.model.entity.Users;
import com.edts.edtstechnicaltest.model.request.AuthRequest;
import com.edts.edtstechnicaltest.model.response.LoginResponse;
import com.edts.edtstechnicaltest.model.response.UserRegisterResponse;
import com.edts.edtstechnicaltest.repository.UserRepository;
import com.edts.edtstechnicaltest.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.expires}")
    private int expireIn;

    public UserRegisterResponse userRegister(AuthRequest request) {
        String encodedPass = passwordEncoder.encode(request.getPassword());

        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(encodedPass);

        Users createdUser;
        try {
            createdUser = userRepository.save(users);
        } catch (DataIntegrityViolationException e) {
            log.error("Duplicate username. {}", e.getMessage());
            throw new DataAlreadyExistException("Duplicate Username");
        }

        return UserRegisterResponse.builder()
                .username(createdUser.getUsername())
                .createdDate(createdUser.getCreatedDate())
                .build();
    }

    public LoginResponse login(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(request.getUsername(), expireIn);

            return LoginResponse.builder()
                    .accessToken(token)
                    .expiresIn(expireIn)
                    .tokenType("Bearer ")
                    .build();
        } catch (AuthenticationException authExc) {
            throw new BadCredentialsException("Invalid Login Credentials");
        }
    }


}
