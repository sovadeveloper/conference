package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public String getAllUsers(@AuthenticationPrincipal UserEntity currentUser, Model model) throws Exception {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "usersList";
    }
}
