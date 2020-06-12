package com.example.demo.serviceimpl;

import com.example.demo.authentication.AuthenticationAccount;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByUsernameWhenAccountOptionalIsPresent() {
        Account account = new Account();
        account.setId(1);
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepository.findByUsername(anyString())).thenReturn(accountOptional);

        assertEquals(accountOptional.get(),accountService.findByUsername(anyString()));
    }

    @Test
    void findByUsernameWhenAccountOptionalIsNotPresent() {
        Optional<Account> accountOptional = Optional.empty();

        when(accountRepository.findByUsername(anyString())).thenReturn(accountOptional);

        assertNull(accountService.findByUsername(anyString()));
    }

    @Test
    void loadUserByUsernameWhenAccountOptionalIsPresent(){
        Account account = new Account();
        account.setId(1);
        account.setUsername("abc");
        account.setPassword("abc12345");
        Role role  = new Role();
        role.setId(1);
        role.setName("ROLE_LECTURER");
        account.setRole(role);
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepository.findByUsername(anyString())).thenReturn(accountOptional);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_LECTURER"));
        UserDetails expected = new AuthenticationAccount("abc","abc12345",grantedAuthorities);

        UserDetails actual = accountService.loadUserByUsername(anyString());

        assertEquals(expected.getUsername(),actual.getUsername());
        assertEquals(expected.getPassword(),actual.getPassword());
        assertEquals(1,actual.getAuthorities().size());

    }

    @Test
    void loadUserByUsernameWhenAccountOptionalIsNotPresent(){
        Optional<Account> accountOptional = Optional.empty();

        when(accountRepository.findByUsername(anyString())).thenReturn(accountOptional);

        assertThrows(UsernameNotFoundException.class, () -> {
            accountService.loadUserByUsername(anyString());
        });
    }
}