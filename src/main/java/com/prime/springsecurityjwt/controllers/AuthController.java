package com.prime.springsecurityjwt.controllers;

import com.prime.springsecurityjwt.models.LoginRequest;
import com.prime.springsecurityjwt.services.TokenService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin){
        log.info("hitting this yeeee-------------");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        log.debug("Token requested for user :{}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.info("Token granted {}", token);
        return token;
    }
}
