package com.cyberspeed.chatserver.service;

import com.cyberspeed.chatserver.entity.Message;

import java.util.List;

public interface ChatService {

    public String sendMessage();

    List<Message> chat(Long roomId);
}
