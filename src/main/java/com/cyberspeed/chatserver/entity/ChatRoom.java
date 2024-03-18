package com.cyberspeed.chatserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHAT_ROOM")
public class ChatRoom {

    @Id
    @Column(name = "ROOM_ID")
    //unique among all users
    private Long roomNumber;

    //can be duplicate but unique with same user while creating
    @Column(name = "CHAT_ROOM_NAME")
    private String chatRoomName;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_ON", updatable = false)
    private LocalDateTime createdOn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    List<Message> messages;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    List<User> participants;
}
