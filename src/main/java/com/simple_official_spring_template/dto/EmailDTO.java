package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    private String subject;
    private String message;
    private String[] receivedEmails;

    public EmailDTO() {
    }

    public EmailDTO(String subject, String message, String[] receivedEmails) {
        this.subject = subject;
        this.message = message;
        this.receivedEmails = receivedEmails;
    }
}
