package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.SmsDTO;
import com.simple_official_spring_template.service.IFileProcessing;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.OutgoingCallerId;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TestAPI {

    @Autowired private IFileProcessing processing;

    @PostMapping(value = "/api-test")
    public void getCategories(@RequestBody MultipartFile file) throws IOException {
        System.out.println("Hii");

        processing.processCsvFile(file);
    }
}
