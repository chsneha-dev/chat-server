package com.cyberspeed.chatserver.controller;

import com.cyberspeed.chatserver.entity.Message;
import com.cyberspeed.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {


    private ChatService chatService;

    @GetMapping("/chat/{roomId}")
    public ResponseEntity<List<Message>> test(@PathVariable Long roomId) {
        return ResponseEntity.ok().body(chatService.chat(roomId));
    }
}
