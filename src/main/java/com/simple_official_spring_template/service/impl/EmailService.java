package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.dto.EmailDTO;
import com.simple_official_spring_template.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(emailDTO.getReceivedEmails());

//        msg.setTo("dinhvb1993@gmail.com", "bangdinh1993@gmail.com");

        msg.setSubject(emailDTO.getSubject());
        msg.setText(emailDTO.getMessage());

        javaMailSender.send(msg);
    }
}
