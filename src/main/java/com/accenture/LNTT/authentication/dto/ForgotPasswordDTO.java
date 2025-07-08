package com.accenture.LNTT.authentication.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]\\.[a-zA-Z]{2,}$", message = "Please enter valid email")
    private String email;
}
