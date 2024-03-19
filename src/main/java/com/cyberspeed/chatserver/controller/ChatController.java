package com.cyberspeed.chatserver.controller;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.dto.MessageResp;
import com.cyberspeed.chatserver.entity.Message;
import com.cyberspeed.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat1")
    @SendTo("/topic/messages")
    public MessageResp chatSocket(@Payload MessageDto message) {
        return new MessageResp(message.getClientId(), message.getData(), LocalDateTime.now());
    }

}
