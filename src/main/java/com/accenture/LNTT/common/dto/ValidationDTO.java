package com.accenture.LNTT.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationDTO {
    private String errorField;
    private String errorMessage;
}
