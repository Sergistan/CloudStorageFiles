package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.ErrorUnauthorized;
import com.example.cloudstoragefiles.models.User;
import com.example.cloudstoragefiles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null){
            throw new ErrorUnauthorized();
        }
        return user;
    }
}
