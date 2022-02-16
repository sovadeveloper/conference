package com.sovadeveloper.conference.routes;

import com.sovadeveloper.conference.entities.Role;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public SecurityController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserEntity userEntity) throws Exception {
        UserEntity userEntityFromDb = userRepo.findByUsername(userEntity.getUsername());
        if(userEntityFromDb != null){
            throw new Exception("Такой пользователь уже существует");
        }
        if(userEntity.getPassword().length() < 1){
            throw new Exception("Пароль не может быть пустым");
        }
        userEntity.setActive(true);
        userEntity.setRole(Role.LISTENER);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
        return "redirect:/login";
    }
}
