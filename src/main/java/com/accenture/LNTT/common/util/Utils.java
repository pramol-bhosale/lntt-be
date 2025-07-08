package com.accenture.LNTT.common.util;

import com.accenture.LNTT.user.entity.UserEntity;
import com.accenture.LNTT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    private final UserRepository userRepository;

    @Autowired
    public Utils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static String getCurrentUserEmail() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public UserEntity getCurrentUser() {
        return userRepository.findByEmailIgnoreCase(getCurrentUserEmail());
    }
}
