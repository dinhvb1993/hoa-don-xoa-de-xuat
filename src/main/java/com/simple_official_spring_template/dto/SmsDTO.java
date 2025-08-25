package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsDTO {
    private String number;
    private String content;
}
