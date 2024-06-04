package com.example.test.service;


import com.example.test.entity.AppUser;
import com.example.test.security.enumf.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, AppUser> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();
        //username:passwowrd -> admin:admin
        data.put("admin", new AppUser("admin", "+f4i1iURW6nUyGK60vfJaWYTWHUi4S88Ef2szj3N16U=", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<AppUser> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }
}