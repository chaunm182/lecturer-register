package com.example.demo.authentication;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AccountService accountService;

    private Logger logger = Logger.getLogger(getClass().getName());
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        logger.info("=============> Custom authentication success handler");
        String username = authentication.getName();
        Account account = accountService.findByUsername(username);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("account",account);
        httpServletResponse.sendRedirect("/");
    }
}
