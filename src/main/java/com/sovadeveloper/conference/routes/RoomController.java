package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public String showAllRooms(@AuthenticationPrincipal UserEntity currentUser, Model model) throws Exception {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "index";
    }

    @GetMapping("/room/{roomId}")

    public String showScheduleForRoom(@AuthenticationPrincipal UserEntity currentUser,
                                      @PathVariable Long roomId, Model model) throws Exception {
        model.addAttribute("room", roomService.getById(roomId));
        model.addAttribute("schedule", roomService.getScheduleByRoom(roomId));
        model.addAttribute("currentUser", currentUser);
        System.out.println(currentUser);
        return "scheduleByRoom";
    }
}
