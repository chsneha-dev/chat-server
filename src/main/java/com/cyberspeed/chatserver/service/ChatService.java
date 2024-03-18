package com.cyberspeed.chatserver.service;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.entity.Message;

import java.util.List;

public interface ChatService {

    String sendMessage(MessageDto messageDto);

    ChatHistory chat(Long roomId);
}
