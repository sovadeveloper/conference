package com.sovadeveloper.conference.controllers;

import com.sovadeveloper.conference.dto.TalkDTO;
import com.sovadeveloper.conference.entities.TalkEntity;
import com.sovadeveloper.conference.entities.UserEntity;
import com.sovadeveloper.conference.services.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/talk")
public class TalkRestController {
    private final TalkService talkService;

    @Autowired
    public TalkRestController(TalkService talkService) {
        this.talkService = talkService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(talkService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(talkService.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TalkEntity talkEntity, @AuthenticationPrincipal UserEntity currentUser){
        try {
            return ResponseEntity.ok(talkService.create(talkEntity, currentUser));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody TalkEntity talkEntity){
        try {
            return ResponseEntity.ok(talkService.edit(id, talkEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity deleteUserFromTalk(@RequestBody TalkEntity talkEntityUpdated){
        try {
            return ResponseEntity.ok(talkService.deleteUserFromTalk(talkEntityUpdated));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(talkService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
