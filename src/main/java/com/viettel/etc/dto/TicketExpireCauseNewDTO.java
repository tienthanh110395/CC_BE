package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketExpireCauseNewDTO {

    Long ticketExpireCauseId;
    Long ticketExpireCauseOneId;
    Long ticketExpireCauseTwoId;
    Long ticketExpireCauseThreeId;

    String expireCauseName;
    String expireCauseNameParent;

    String expireCauseCode;

    Long parentId;
    Long parentOneId;
    Long parentTwoId;
    Long parentThreeId;

    String description;

    Long status;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    List<String> statusType;

    Long levelExpire;

    Long ticketExpireLevelOne;
    Long ticketExpireLevelTwo;
    List<Long> lstIdsActive;

    List<Long> lstIdsInactive;

    Long actTypeId;
}
