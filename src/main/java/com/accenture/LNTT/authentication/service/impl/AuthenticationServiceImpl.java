package com.accenture.LNTT.authentication.service.impl;

import com.accenture.LNTT.authentication.dto.ForgotPasswordDTO;
import com.accenture.LNTT.authentication.dto.LoginCredentialsDTO;
import com.accenture.LNTT.authentication.dto.RegistrationDTO;
import com.accenture.LNTT.authentication.service.AuthenticationService;
import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.common.dto.ValidationDTO;
import com.accenture.LNTT.common.enums.Constants;
import com.accenture.LNTT.common.util.ResultBuilder;
import com.accenture.LNTT.user.entity.UserEntity;
import com.accenture.LNTT.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResultDTO register(RegistrationDTO registrationDTO) {
        try {
            List<ValidationDTO> validationDTOList = validateRegistration(registrationDTO);
            if (!validationDTOList.isEmpty()) {
                log.warn("Validation failed for user registration with email: {}", registrationDTO.getEmail());
                return ResultBuilder.failure(Constants.VALIDATION_FAILED, validationDTOList);
            }

            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(registrationDTO, userEntity, "password");
            userEntity.setUsername(registrationDTO.getEmail());
            userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

            userRepository.save(userEntity);
            log.info("User registered successfully");
            return ResultBuilder.success();
        } catch (Exception e) {
            log.error("Error while registering user", e);
            return ResultBuilder.failure(Constants.OPERATION_FAILED);
        }

    }

    @Override
    public ResultDTO login(LoginCredentialsDTO loginCredentialsDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentialsDTO.getEmail(), loginCredentialsDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResultBuilder.success(Constants.LOGIN_SUCCESSFULLY, jwtService.generateToken(loginCredentialsDTO.getEmail()));
            } else {
                return ResultBuilder.failure(Constants.INVALID_CREDENTIALS);
            }
        } catch (Exception e) {
            log.error("Error while login", e);
            return ResultBuilder.failure(Constants.OPERATION_FAILED);
        }
    }

    @Override
    public ResultDTO forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        return null;
    }


    public List<ValidationDTO> validateRegistration(RegistrationDTO registrationDTO) {
        List<ValidationDTO> validationDTOList = new ArrayList<>();
        try {
            boolean isExistsByEmail = userRepository.existsByEmailIgnoreCase(registrationDTO.getEmail());
            if (isExistsByEmail) {
                log.info("User with email {} already exists", registrationDTO.getEmail());
                validationDTOList.add(new ValidationDTO("email", "Email Already exists!"));
            }

        } catch (Exception e) {
            log.error("Error while validating user", e);
        }
        return validationDTOList;
    }

}
