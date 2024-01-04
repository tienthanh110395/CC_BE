package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDetailDTO {
    Long cusTypeId;

    String userName;

    String userId;

    String birth;

    String gender;

    String address;

    String identifier;

    String repIdentifier;

    Long repIdentifierType;

    String dateOfIssue;

    String placeOfIssue;

    String contractNo;

    String signDate;

    String effDate;

    String expDate;

    String phone;

    String email;

    String customerId;

    String contractId;

    Long documentType;

    Long isAdditional;

    Long isAlertMoney;

    Long alertMoney;

    String smsNotification;

    String accountAlias;

    String billCycle;

    String billCycleMergeType;

    String noticeAreaName;

    String noticeStreet;

    String noticeAreaCode;

    String noticeEmail;

    String noticePhoneNumber;
}
