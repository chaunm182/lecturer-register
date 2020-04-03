package com.example.demo.serviceimpl;

import com.example.demo.authentication.AuthenticationAccount;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(s);
        if(!accountOptional.isPresent()) throw new UsernameNotFoundException("Username not Found: "+s);
        Account account = accountOptional.get();
        Role role = account.getRole();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getName()));
        AuthenticationAccount authenticationAccount = new AuthenticationAccount(
                account.getUsername(),account.getPassword(),roles);

        return authenticationAccount;
    }

    @Override
    public Account findByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(!accountOptional.isPresent()) return null;
        return accountOptional.get();
    }
}
