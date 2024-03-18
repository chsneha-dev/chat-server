package com.cyberspeed.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Long id;

    @NotBlank
    private String clientId;

    @NonNull
    private Long roomId;

    @NotEmpty
    private String data;

    //if photo/video/doc is uploaded unique id of the document
    private String attachmentId;
    private LocalDateTime sentOn;

}
