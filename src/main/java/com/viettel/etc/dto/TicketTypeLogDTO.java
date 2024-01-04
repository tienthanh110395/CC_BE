package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TicketTypeLogDTO {
    Long ticketTypeLogId;
    Long actType;
    String tableName;
//    Long levelTt;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    Integer startRecord;
    Integer pageSize;
    Date fromDate;
    Date toDate;
    Long cateType;
    Long impactType;
}
