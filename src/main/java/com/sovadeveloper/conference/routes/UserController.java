package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.UserService;
import com.sovadeveloper.conference.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("user/profile/{id}")
    public String getUser(@AuthenticationPrincipal UserEntity currentUser, Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", Role.values());
        return "userProfile";
    }
}
