package com.cyberspeed.chatserver.controller;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat/{roomId}")
    public ResponseEntity<ChatHistory> chat(@PathVariable Long roomId) {
        return ResponseEntity.ok().body(chatService.chat(roomId));
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMesssage(@RequestBody @Validated MessageDto messageDto) {
        return ResponseEntity.ok().body(chatService.sendMessage(messageDto));
    }

}
