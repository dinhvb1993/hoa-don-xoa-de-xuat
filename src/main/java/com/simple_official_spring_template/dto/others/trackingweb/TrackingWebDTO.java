package com.simple_official_spring_template.dto.others.trackingweb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingWebDTO {
    private Integer id; // hostname
    private String hostName; // hostname
    private String ip;
    private String gclid;
    private Boolean autoTool;
    private String date; // yyyy-mm-dd
    private Timestamp updateTime;

    private Integer[] ids;
}
