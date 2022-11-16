package edu.max.bstore.service;

import edu.max.bstore.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.LoginException;

public interface UserService extends UserDetailsService {
    void register(User user);

    void checkCredentials(String userId, String userSecret) throws LoginException;
}