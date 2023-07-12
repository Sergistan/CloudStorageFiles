package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthRepository {

    private static final Map<String, User> mapAuthenticationUsers = new ConcurrentHashMap<>();

    public void saveAuthenticationUser (String authToken, User user) {
        mapAuthenticationUsers.put(authToken,user);
    }

    public void deleteAuthenticationUserByToken (String authToken) {
            mapAuthenticationUsers.remove(authToken);
    }

    public User getUserByToken (String authToken) {
        return mapAuthenticationUsers.get(authToken);
    }

}
