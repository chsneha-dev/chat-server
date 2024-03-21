package com.cyberspeed.chatserver.controller;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.dto.MessageResp;
import com.cyberspeed.chatserver.entity.Message;
import com.cyberspeed.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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


    @DeleteMapping("/message/{messageId}")
    public ResponseEntity sendMesssage(@PathVariable Long messageId) {
        chatService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    //Web sockets - this will establish the connection and done close untill notified
    //client to server communication can heppen both sides
    // need to develop to test this web socket

    @MessageMapping("/chat1")
    @SendTo("/topic/messages")
    public MessageResp chatSocket(@Payload MessageDto message) {
        return new MessageResp(message.getClientId(), message.getData(), LocalDateTime.now());
    }


    //if we use any DB that supports Reactive programming we can use below API
    // it will stream the data from table lively

    //the connection will establish lively and uses http
    //server will push the data to client when evever the new event happend
    // here we are using h2 which doesn't support reactive so this API wont stream lively

    @GetMapping(value = "/chat/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Message> streamChatMessagesWithFlux() {
        return chatService.streamMsges();
    }

}
