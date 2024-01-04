package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;

import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Autogen class DTO: 
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:12 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketStatisticDTO {

    Long statisticId;

    String contractNoUserName;

    String plateNumber;

    String systemPhoneNumber;

    String callPhoneNumber;

    Long sourceId;

    Long l1StatisticTypeId;

    Long l2StatisticTypeId;

    Long l3StatisticTypeId;

    Long l4StatisticTypeId;

    Long l5StatisticTypeId;

    String statisticContent;

    String custReaction;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date fromDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date toDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    String l1StatisticTypeName;

    String l2StatisticTypeName;

    String l3StatisticTypeName;

    String l4StatisticTypeName;

    String l5StatisticTypeName;

    String sourceName;

    String contractNoUserNames;

    String plateNumbers;

    String systemPhoneNumbers;

    String callPhoneNumbers;

    List<String> reactionCustomerType;

    Boolean resultSqlEx;

    Integer startrecord;

    Integer pagesize;
}