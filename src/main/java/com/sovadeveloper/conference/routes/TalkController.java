package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.TalkService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TalkController {
    private final TalkService talkService;

    public TalkController(TalkService talkService) {
        this.talkService = talkService;
    }

    @GetMapping("/talk/list")
    public String getAllTalksForCurrentUser(@AuthenticationPrincipal UserEntity currentUser, Model model) throws Exception {
        model.addAttribute("talks", talkService.getByUser(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "talkList";
    }

    @PostMapping("/talk")
    public String createTalk(@AuthenticationPrincipal UserEntity currentUser, TalkEntity talkEntity) throws Exception {
        talkService.create(talkEntity, currentUser);
        return "redirect:/talk/list";
    }
}
