package com.cyberspeed.chatserver.service;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.entity.Message;
import reactor.core.publisher.Flux;

public interface ChatService {

    String sendMessage(MessageDto messageDto);

    ChatHistory chat(Long roomId);

    Flux<Message> streamMsges();

    void deleteMessage(Long messageId);
}
