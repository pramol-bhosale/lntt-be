package com.accenture.LNTT.common.util;

import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.common.enums.Constants;

public class ResultBuilder {

    public static ResultDTO success(Constants message) {
        return ResultDTO
                .builder()
                .status(true)
                .message(message.getValue())
                .build();
    }

    public static ResultDTO success() {
        return ResultDTO
                .builder()
                .status(true)
                .message(Constants.OPERATION_SUCCESSFULLY.getValue())
                .build();
    }

    public static ResultDTO success(Constants message, Object data) {
        return ResultDTO
                .builder()
                .status(true)
                .message(message.getValue())
                .data(data)
                .build();
    }

    public static ResultDTO failure(Constants message) {
        return ResultDTO
                .builder()
                .status(false)
                .message(message.getValue())
                .build();
    }

    public static ResultDTO failure() {
        return ResultDTO
                .builder()
                .status(false)
                .message(Constants.OPERATION_FAILED.getValue())
                .build();
    }

    public static ResultDTO failure(Constants message, Object data) {
        return ResultDTO
                .builder()
                .status(false)
                .message(message.getValue())
                .data(data)
                .build();
    }

}
