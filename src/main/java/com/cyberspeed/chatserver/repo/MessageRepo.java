package com.cyberspeed.chatserver.repo;

import com.cyberspeed.chatserver.entity.ChatRoom;
import com.cyberspeed.chatserver.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}
