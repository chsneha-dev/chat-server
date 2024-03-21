package com.cyberspeed.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    private Long id;

    //can be validated this against the existing users
    @NotBlank
    private String clientId;

    private Long roomId;

    @NotEmpty
    private String data;

    //if photo/video/doc is uploaded unique id of the document
    private String attachmentId;
    private LocalDateTime sentOn;

}
