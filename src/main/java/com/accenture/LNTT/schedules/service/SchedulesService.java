package com.accenture.LNTT.schedules.service;

import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.schedules.dto.SchedulesDto;
import org.springframework.data.domain.Pageable;

public interface SchedulesService {
    ResultDTO create(SchedulesDto schedulesDto);

    ResultDTO update(String id, SchedulesDto schedulesDto);

    ResultDTO delete(String id);

    ResultDTO getAllByCurrentUser(String month, Pageable pageable);

    ResultDTO getAllAvailableSMEs(String fromDate, String toDate, String fromTime, String toTime, Pageable pageable);
}
