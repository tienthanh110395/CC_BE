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
public class TicketLevelCateDTO {
    List<Long> lstLevelCateId;
    Long search;
    Long ticketLevelCateId;
    String ticketLevelCateName;
    String ticketLevelCateCode;
    Long status;
    String description;
    Long isActive;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    Long type;
    Integer startRecord;
    Integer pageSize;
    String fromDate;
    String toDate;
    List<Long> lstIdsActive;
    List<Long> lstIdsInactive;

    Long ticketSlaId;

    Long ticketTimeFull;
    Long ticketTimeLv1;
    Long ticketTimeLv2;
    Long ticketRetime;
//    Long levelTt;
}
