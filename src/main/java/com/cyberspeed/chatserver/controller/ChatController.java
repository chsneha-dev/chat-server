package com.cyberspeed.chatserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {


    @GetMapping("/chat")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("Success");
    }
}
