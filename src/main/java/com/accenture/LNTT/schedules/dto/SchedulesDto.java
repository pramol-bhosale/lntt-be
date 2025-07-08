package com.accenture.LNTT.schedules.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SchedulesDto {
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "Please enter date in valid format YYYY-MM-DD")
    private String fromDate;
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "Please enter date in valid format YYYY-MM-DD")
    private String toDate;
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Please enter time in valid format HH:MM:SS")
    private String fromTime;
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Please enter time in valid format HH:MM:SS")
    private String toTime;
    @NotNull(message = "Activity cannot be null")
    @NotBlank(message = "Activity cannot be blank")
    private String activity;
    private String description;
}
