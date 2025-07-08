package com.accenture.LNTT.authentication.service;

import com.accenture.LNTT.authentication.dto.ForgotPasswordDTO;
import com.accenture.LNTT.authentication.dto.LoginCredentialsDTO;
import com.accenture.LNTT.authentication.dto.RegistrationDTO;
import com.accenture.LNTT.common.dto.ResultDTO;

public interface AuthenticationService {
    ResultDTO register(RegistrationDTO registrationDTO);
    ResultDTO login(LoginCredentialsDTO loginCredentialsDTO);
    ResultDTO forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
}
