package com.simple_official_spring_template.dto.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdsAccountDTO {
    private Long id;
    private String status;
    private String descriptiveName;
    private Long parentAccountId;
    private Long loginCustomerId;
    private String ocid;
    private String ascid;
    private String c;

    private Double totalBalance;
    private Double lastPaymentAmount;
    private String lastPaymentDate;
    private String paymentCheckTime;

    private String lastCheckDomainDate;

    private Timestamp updateTime;
    private Timestamp toolCheckingTime;
    private Timestamp notificationCheckingTime;

    private String notification;
    private String directManager;
    private String paymentsProfile;
    private String paymentsNumber;

    private Boolean activeList;
    private Boolean pausedList;
    private Boolean pausedList2;
}
