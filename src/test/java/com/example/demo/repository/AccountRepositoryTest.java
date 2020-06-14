package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Person;
import com.example.demo.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUsername() {
        Optional<Account> account = accountRepository.findByUsername("gv01");
        assertNotNull(account.get());
    }

    @Test
    void findByUsername1() {
        Optional<Account> account = accountRepository.findByUsername("gvưoeij");
        assertFalse(account.isPresent());
    }

    @Test
    void findById(){
        Optional<Account> account = accountRepository.findById(1);
        assertNotNull(account.get());
    }

    @Test
    void findById1(){
        Optional<Account> account = accountRepository.findById(9999);
        assertFalse(account.isPresent());
    }

    @Test
    void save(){
        Account account = new Account();
        Role role= new Role();
        role.setId(1);
        account.setRole(role);
        account.setUsername("sèohi");
        account.setPassword("aibcudshoi124s");
        account.setEmail("ovno@gmail.com");
        account.setPerson(new Person());

        assertNotNull(accountRepository.save(account));
    }

    @Test
    void delete(){
        accountRepository.deleteById(1);
        assertFalse(accountRepository.findById(1).isPresent());
    }
}