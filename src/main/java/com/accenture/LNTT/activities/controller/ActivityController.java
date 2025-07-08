package com.accenture.LNTT.activities.controller;

import com.accenture.LNTT.activities.service.ActivityService;
import com.accenture.LNTT.common.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/all")
    public ResultDTO getAllActivities() {
        log.info("Fetching all activity details from database");
        return activityService.getAll();
    }
}
