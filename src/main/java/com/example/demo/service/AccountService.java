package com.example.demo.service;

import com.example.demo.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AccountService extends UserDetailsService {
    Account findByUsername(String username);
}
