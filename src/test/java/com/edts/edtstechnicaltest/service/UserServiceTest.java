package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.exception.DataAlreadyExistException;
import com.edts.edtstechnicaltest.model.entity.Users;
import com.edts.edtstechnicaltest.model.request.AuthRequest;
import com.edts.edtstechnicaltest.model.response.UserRegisterResponse;
import com.edts.edtstechnicaltest.repository.UserRepository;
import com.edts.edtstechnicaltest.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;
    private Users mockUser;
    private AuthRequest mockAuthRequest;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository, authenticationManager, jwtUtil, passwordEncoder);
        mockUser = buildUser();
        mockAuthRequest = buildAuthRequest();
    }

    @Test
    public void testRegisterUser_returnSuccess() {
        when(userRepository.save(any())).thenReturn(mockUser);
        UserRegisterResponse response = userService.userRegister(mockAuthRequest);

        assertEquals("TEST_USERNAME", response.getUsername());
        verify(userRepository, times(1)).save(any());
        verify(passwordEncoder, times(1)).encode(any());
    }

    @Test
    public void testRegisterUser_withDuplicateUsername_throwDataAlreadyExistException() {
        when(userRepository.save(any())).thenThrow(new DataIntegrityViolationException("Duplicate Username"));
        assertThrows(DataAlreadyExistException.class, () -> {
            userService.userRegister(mockAuthRequest);
        });
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testLoginUser_withValidCredentials_returnAccessToken() {
        userService.login(mockAuthRequest);
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    public void testLoginUser_withInvalidCredentials_throwBadCredentialsException() {
        when(authenticationManager.authenticate(any())).thenThrow(new CredentialsExpiredException("Invalid Credentials"));
        assertThrows(BadCredentialsException.class, () -> {
            userService.login(mockAuthRequest);
        });
        verify(authenticationManager, times(1)).authenticate(any());
    }

    private Users buildUser() {
        Users users = new Users();
        users.setId(1L);
        users.setUsername("TEST_USERNAME");
        users.setPassword("TEST_PASSWORD");
        users.setCreatedDate(new Date());
        users.setUpdatedDate(new Date());
        return users;
    }

    private AuthRequest buildAuthRequest() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("TEST_USERNAME");
        authRequest.setPassword("TEST_PASSWORD");
        return authRequest;
    }

}
