package com.cyberspeed.chatserver;

import com.cyberspeed.chatserver.dto.ChatHistory;
import com.cyberspeed.chatserver.dto.MessageDto;
import com.cyberspeed.chatserver.entity.Message;
import com.cyberspeed.chatserver.repo.MessageRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ChatServerApplication.class})
public class ChatServerApplicationTests {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    MessageRepo messageRepo;

    @BeforeEach
    void setup() {
    }

    @BeforeAll
    static void execBeforeAll() {
    }

    @AfterAll
    static void execAfterAll() {
    }


    @Test
    @DisplayName("Send message by ")
    @SneakyThrows
    @Order(1)
    void testSendMsgSuccess() {

        MessageDto messageDto = new MessageDto();
        messageDto.setClientId("Soni");
        messageDto.setRoomId(123L);
        messageDto.setData("Hello !!");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/api/message"))
                .content(objectMapper.writeValueAsString(messageDto))
                .header("Authorization", "Basic dGVzdDp0ZXN0")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("Success");

        List<Message> messageList = messageRepo.findAll();
        Assertions.assertThat(messageList).hasSize(1);
        Assertions.assertThat(messageList.get(0).getData()).isEqualTo("Hello !!");

    }

    @Test
    @DisplayName("Chat History")
    @SneakyThrows
    @Order(2)
    void chatHistory() {

        MessageDto messageDto = new MessageDto();
        messageDto.setClientId("Soni");
        messageDto.setRoomId(123L);
        messageDto.setData("Hello !!");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/api/chat/123"))
                .header("Authorization", "Basic dGVzdDp0ZXN0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        ChatHistory response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ChatHistory.class);
        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getRoomId()).isEqualTo(messageDto.getRoomId());
        Assertions.assertThat(response.getMessages().get(0).getClientId()).isEqualTo(messageDto.getClientId());
        Assertions.assertThat(response.getMessages().get(0).getData()).isEqualTo(messageDto.getData());

    }


    @Test
    @DisplayName("get Chat History for invalid room Id ")
    @SneakyThrows
    @Order(3)
    void testSendMsgFailed() {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/api/chat/444"))
                .header("Authorization", "Basic dGVzdDp0ZXN0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(500);
        Assertions.assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("Invalid Room !!");

    }

}
