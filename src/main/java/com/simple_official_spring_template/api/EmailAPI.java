package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.EmailDTO;
import com.simple_official_spring_template.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailAPI {
    @Autowired
    private IEmailService emailService;


    @PostMapping(value = "/api-email")
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);
    }

}
