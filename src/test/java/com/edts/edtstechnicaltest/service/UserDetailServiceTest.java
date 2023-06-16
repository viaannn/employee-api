package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.model.entity.Users;
import com.edts.edtstechnicaltest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserDetailService userDetailService;
    private Users mockUser;


    @BeforeEach
    public void init() {
        userDetailService = new UserDetailService(userRepository);
        mockUser = buildUser();
    }

    @Test
    public void testLoadUserByUsername_returnUser() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(mockUser));
        UserDetails userDetails = userDetailService.loadUserByUsername(mockUser.getUsername());

        assertEquals(mockUser.getUsername(), userDetails.getUsername());
        verify(userRepository, times(1)).findByUsername(any());
    }

    @Test
    public void testLoadUserByUsername_invalidUsername_throwUsernameNotFoundException() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailService.loadUserByUsername(mockUser.getUsername());
        });
        verify(userRepository, times(1)).findByUsername(any());
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
}
