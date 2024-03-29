package com.cyberspeed.chatserver.service.impl;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.entity.Message;
import com.cyberspeed.chatserver.exception.ChatException;
import com.cyberspeed.chatserver.repo.ChatRoomRepo;
import com.cyberspeed.chatserver.repo.MessageRepo;
import com.cyberspeed.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepo roomRepo;
    private final MessageRepo messageRepo;


    @Override
    public String sendMessage(MessageDto messageDto) {
        messageRepo.save(messageDtoToEntity(messageDto));
        return "Success";
    }

    private Message messageDtoToEntity(MessageDto messageDto) {
        return Message.builder()
                .clientId(messageDto.getClientId())
                .fkRoomId(messageDto.getRoomId())
                .data(messageDto.getData())
                .attachmentId(messageDto.getAttachmentId())
                .sentOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();
    }

    @Override
    public ChatHistory chat(Long roomId) {

        return roomRepo.findById(roomId).map(room ->
                ChatHistory.builder()
                        .roomId(roomId)
                        .messages(room.getMessages() == null ? null : room.getMessages().stream().map(msg -> new MessageDto(msg.getId(), msg.getClientId(), msg.getFkRoomId(), msg.getData(), msg.getAttachmentId(), msg.getSentOn())).toList()).build()
        ).orElseThrow(() -> new ChatException("Invalid Room !!"));
    }

    @Override
    public Flux<Message> streamMsges() {
        return Flux.fromIterable(messageRepo.findAll());
    }

    @Override
    public void deleteMessage(Long messageId) {
        messageRepo.deleteById(messageId);
    }
}
