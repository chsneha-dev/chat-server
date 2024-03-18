package com.cyberspeed.chatserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    // assuming client id in the message as name in user
    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE_NO")
    private String mobile;

    @Column(name = "CREATED_ON", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
}
