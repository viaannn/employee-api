package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.model.entity.Users;
import com.edts.edtstechnicaltest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userRes = userRepository.findByUsername(username);
        if (userRes.isEmpty()) {
            log.error("User is not found");
            throw new UsernameNotFoundException("Could not findUser with username = " + username);
        }

        Users user = userRes.get();
        return new User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYEE")));
    }
}
