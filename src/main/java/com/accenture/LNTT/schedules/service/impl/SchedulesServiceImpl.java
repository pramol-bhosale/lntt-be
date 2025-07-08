package com.accenture.LNTT.schedules.service.impl;

import com.accenture.LNTT.activities.entity.ActivityEntity;
import com.accenture.LNTT.activities.repository.ActivityRepository;
import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.common.enums.Constants;
import com.accenture.LNTT.common.util.ResultBuilder;
import com.accenture.LNTT.common.util.Utils;
import com.accenture.LNTT.schedules.dto.SchedulesDto;
import com.accenture.LNTT.schedules.entity.AvailableSMEResultEntity;
import com.accenture.LNTT.schedules.entity.SchedulesEntity;
import com.accenture.LNTT.schedules.repository.SchedulesRepository;
import com.accenture.LNTT.schedules.service.SchedulesService;
import com.accenture.LNTT.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;

@Service
@Slf4j
public class SchedulesServiceImpl implements SchedulesService {

    private final SchedulesRepository schedulesRepository;
    private final Utils utils;
    private final ActivityRepository activityRepository;

    @Autowired
    public SchedulesServiceImpl(SchedulesRepository schedulesRepository, Utils utils, ActivityRepository activityRepository) {
        this.schedulesRepository = schedulesRepository;
        this.utils = utils;
        this.activityRepository = activityRepository;
    }


    @Override
    public ResultDTO create(SchedulesDto schedulesDto) {
        try {

            SchedulesEntity schedules = new SchedulesEntity();
            schedules = mapToEntity(schedules, schedulesDto);
            if (schedules == null) {
                return ResultBuilder.failure();
            }

            ActivityEntity activity = activityRepository.findById(schedulesDto.getActivity()).orElse(null);
            if (activity == null) {
                log.warn("Activity not given for schedules");
                return ResultBuilder.failure(Constants.VALIDATION_FAILED);
            }
            schedules.setActivity(activity);
            schedules.setUser(utils.getCurrentUser());
            schedules = schedulesRepository.save(schedules);

            return ResultBuilder.success(Constants.OPERATION_SUCCESSFULLY, schedules);
        } catch (Exception e) {
            log.error("Error while creating schedules for user {}", "currentUser", e);
            return ResultBuilder.failure(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResultDTO update(String id, SchedulesDto schedulesDto) {
        try {
            boolean isExists = schedulesRepository.existsById(id);
            if (!isExists) {
                log.warn("Schedules with id {} not found", id);
                return ResultBuilder.failure(Constants.RESOURCE_WITH_GIVEN_ID_NOT_FOUND);
            }

            SchedulesEntity schedules = schedulesRepository.findById(id).get();
            schedules = mapToEntity(schedules, schedulesDto);

            ActivityEntity activity = activityRepository.findById(schedulesDto.getActivity()).orElse(null);
            if (activity == null) {
                log.warn("Activity not given for schedules");
                return ResultBuilder.failure(Constants.VALIDATION_FAILED);
            }
            schedules.setActivity(activity);

            schedules = schedulesRepository.save(schedules);

            return ResultBuilder.success(Constants.OPERATION_SUCCESSFULLY, schedules);
        } catch (Exception e) {
            log.error("Error while updating schedule with id {}", id, e);
            return ResultBuilder.failure();
        }
    }

    @Override
    public ResultDTO delete(String id) {
        try {
            boolean isExists = schedulesRepository.existsById(id);
            if (!isExists) {
                log.warn("Schedules with id {} not found", id);
                return ResultBuilder.failure(Constants.RESOURCE_WITH_GIVEN_ID_NOT_FOUND);
            }

            schedulesRepository.deleteById(id);
            log.info("Schedule with id {} is deleted successfully", id);
            return ResultBuilder.success();

        } catch (Exception e) {
            log.error("Error while deleting schedule {}", id, e);
            return ResultBuilder.failure();
        }
    }

    @Override
    public ResultDTO getAllByCurrentUser(String month, Pageable pageable) {
        try {
            UserEntity user = utils.getCurrentUser();

            Page<SchedulesEntity> page = schedulesRepository.findAllByUserAndInMonth(user.getId(), month, pageable);
            return ResultBuilder.success(Constants.OPERATION_SUCCESSFULLY, page);

        } catch (Exception e) {
            log.error("Error while fetching schedules for user {} with month {}", Utils.getCurrentUserEmail(), month);
            return ResultBuilder.failure();
        }
    }

    @Override
    public ResultDTO getAllAvailableSMEs(String fromDate, String toDate, String fromTime, String toTime, Pageable pageable) {
        try{
            Page<AvailableSMEResultEntity> page= schedulesRepository.findAvailableSMEs(fromDate, toDate, fromTime, toTime, pageable);

            return ResultBuilder.success(Constants.OPERATION_SUCCESSFULLY, page);
        }catch (Exception e){
            log.error("Error while fetching availability of SMEs", e);
            return ResultBuilder.failure();
        }
    }


    private SchedulesEntity mapToEntity(SchedulesEntity schedules, SchedulesDto schedulesDto) {
        try {

            schedules.setFromDate(Date.valueOf(schedulesDto.getFromDate()));
            schedules.setToDate(Date.valueOf(schedulesDto.getToDate()));
            schedules.setFromTime(Time.valueOf(schedulesDto.getFromTime()));
            schedules.setToTime(Time.valueOf(schedulesDto.getToTime()));

            schedules.setDescription(schedulesDto.getDescription());
            return schedules;

        } catch (Exception e) {
            log.error("Error while mapping to schedule entity");
        }
        return null;
    }
}
