package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

public class TicketErrorCauseNewDTO {
    String name;
    String code;
    Long ticketErrorCauseId;
    Long ticketErrorCauseId1;
    Long ticketErrorCauseId2;
    Long ticketErrorCauseId3;
    String ticketErrorCauseName;
    String ticketErrorCauseCode;
    String errorCauseNameParent;
    Long status;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    Integer startRecord;
    Integer pageSize;
    String formDate;
    String toDate;
    List<Long> lstIdsActive;
    List<Long> lstIdsInactive;
    String description;
    Long parentId;
    Long parentIdLv1;
    Long parentIdLv2;
    Long parentIdLv3;
    List<Long> lstParentId;
    Long levelError;
    Long ticketErrorLevelOne;
    Long ticketErrorLevelTwo;
    List<String> statusType;
    Long search;

//    Long levelTt;
}
