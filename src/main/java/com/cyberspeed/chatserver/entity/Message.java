package com.cyberspeed.chatserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CHAT_MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "DATA")
    private String data;

    @Column(name = "ATTACHMENT_ID")
    //if photo/video/doc is uploaded unique id of the document
    private String attachmentId;

    @Column(name = "CREATED_ON", updatable = false)
    private LocalDateTime sentOn;

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
}
