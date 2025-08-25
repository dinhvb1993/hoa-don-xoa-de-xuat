package com.simple_official_spring_template.dto.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerInfoDTO {
    private String id;
    private String keyword;
    // private Set<PhoneNumberDTO> phoneNumbers;
    private String emails; // email list string
    private String sourceType;
    private Boolean needToCheck;
    private Timestamp updateTime;
}
