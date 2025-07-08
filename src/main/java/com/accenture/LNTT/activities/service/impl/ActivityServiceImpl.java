package com.accenture.LNTT.activities.service.impl;

import com.accenture.LNTT.activities.entity.ActivityEntity;
import com.accenture.LNTT.activities.repository.ActivityRepository;
import com.accenture.LNTT.activities.service.ActivityService;
import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.common.enums.Constants;
import com.accenture.LNTT.common.util.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public ResultDTO getAll() {
        try{
            List<ActivityEntity> activityList =  activityRepository.findAll();
            return ResultBuilder.success(Constants.OPERATION_SUCCESSFULLY, activityList);
        }catch (Exception e){
            log.error("Error while fetching all activities from database", e);
            return ResultBuilder.failure();
        }
    }
}
