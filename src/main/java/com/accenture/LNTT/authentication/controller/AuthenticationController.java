package com.accenture.LNTT.authentication.controller;

import com.accenture.LNTT.authentication.dto.ForgotPasswordDTO;
import com.accenture.LNTT.authentication.dto.LoginCredentialsDTO;
import com.accenture.LNTT.authentication.dto.RegistrationDTO;
import com.accenture.LNTT.authentication.service.AuthenticationService;
import com.accenture.LNTT.common.dto.ResultDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResultDTO register(@Valid @RequestBody RegistrationDTO registrationDTO) {
        log.info("Registration request received with email {}", registrationDTO.getEmail());
        return authenticationService.register(registrationDTO);
    }

    @PostMapping("/login")
    public ResultDTO login(@Valid @RequestBody LoginCredentialsDTO loginCredentialsDTO) {
        log.info("Login request received for email {}", loginCredentialsDTO.getEmail());
        return authenticationService.login(loginCredentialsDTO);
    }

    @PostMapping("/forgot-password")
    public ResultDTO forgotPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO ) {
        log.info("Forgot password received for email {}", forgotPasswordDTO.getEmail());
        return authenticationService.forgotPassword(forgotPasswordDTO);
    }

}
