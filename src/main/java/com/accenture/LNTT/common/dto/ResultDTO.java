package com.accenture.LNTT.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO {
    private boolean status;
    private String message;
    private Object data;
}
