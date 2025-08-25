package com.simple_official_spring_template.service;


import com.simple_official_spring_template.dto.EmailDTO;

public interface IEmailService {
    void sendEmail(EmailDTO emailDTO);
}
