package com.accenture.LNTT.schedules.controller;

import com.accenture.LNTT.common.dto.ResultDTO;
import com.accenture.LNTT.common.util.Utils;
import com.accenture.LNTT.schedules.dto.SchedulesDto;
import com.accenture.LNTT.schedules.service.SchedulesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/schedules")
public class SchedulesController {

    private final SchedulesService schedulesService;

    @Autowired
    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @PostMapping
    public ResultDTO create(@Valid @RequestBody SchedulesDto schedulesDto) {
        log.info("Schedule create request received for user {}", Utils.getCurrentUserEmail());
        return schedulesService.create(schedulesDto);
    }

    @PutMapping
    public ResultDTO update(@Valid
                            @RequestParam(name = "id")
                            @Pattern(regexp = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$")
                            String id,
                            @Valid @RequestBody SchedulesDto schedulesDto) {
        log.info("Schedule update request received for id {}", id);
        return schedulesService.update(id, schedulesDto);
    }

    @DeleteMapping
    public ResultDTO delete(@Valid
                            @RequestParam(name = "id")
                            @Pattern(regexp = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$")
                            String id) {
        log.info("Schedules delete request received for id {}", id);
        return schedulesService.delete(id);
    }

    @GetMapping
    public ResultDTO getAllByMonth(@Valid
                                   @RequestParam(name = "month")
                                   @Pattern(regexp = "^\\d{4}-\\d{2}$")
                                   String month,
                                   Pageable pageable) {
        log.info("Fetching schedules for user {} with month {}", Utils.getCurrentUserEmail(), month);
        return schedulesService.getAllByCurrentUser(month, pageable);
    }

    @GetMapping("/available")
    public ResultDTO getAllAvailableSMEs(@RequestParam(name = "from_date") String fromDate,
                                         @RequestParam(name = "to_date") String toDate,
                                         @RequestParam(name = "from_time") String fromTime,
                                         @RequestParam(name = "to_time") String toTime, Pageable pageable){
        log.info("Fetching available SMEs according to given details");
        return schedulesService.getAllAvailableSMEs(fromDate, toDate, fromTime, toTime, pageable);
    }

}
