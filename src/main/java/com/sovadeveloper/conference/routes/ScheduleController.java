package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.ScheduleEntity;
import com.sovadeveloper.conference.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public String addNewSchedule(@RequestBody ScheduleEntity scheduleEntity) throws Exception {
        scheduleService.create(scheduleEntity);
        return "redirect:/talk/list";
    }
}
