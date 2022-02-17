package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.RoomService;
import com.sovadeveloper.conference.services.ScheduleService;
import com.sovadeveloper.conference.services.TalkService;
import com.sovadeveloper.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TalkController {
    private final TalkService talkService;
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final RoomService roomService;

    @Autowired
    public TalkController(TalkService talkService, UserService userService, ScheduleService scheduleService, RoomService roomService) {
        this.talkService = talkService;
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.roomService = roomService;
    }

    @GetMapping("/talk/list")
    public String getAllTalksForCurrentUser(@AuthenticationPrincipal UserEntity currentUser, Model model) throws Exception {
        model.addAttribute("talks", talkService.getByUser(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "talkList";
    }

    @GetMapping("/talk/{id}")
    public String getTalk(@AuthenticationPrincipal UserEntity currentUser,
                          @PathVariable Long id, Model model) throws Exception {
        model.addAttribute("talk", talkService.getById(id));
        model.addAttribute("speakers", userService.getFreeSpeakersForTalk(talkService.getById(id)));
        model.addAttribute("schedule", scheduleService.getScheduleByTalk(talkService.getById(id)));
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "talkProfile";
    }

    @PostMapping("/talk")
    public String createTalk(@AuthenticationPrincipal UserEntity currentUser, TalkEntity talkEntity) throws Exception {
        talkService.create(talkEntity, currentUser);
        return "redirect:/talk/list";
    }
}
