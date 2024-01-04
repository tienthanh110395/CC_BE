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
public class TicketCateStatisticDTO {
    String name;
    String code;
    Long statisticTypeId;
    Long statisticTypeId1;
    Long statisticTypeId2;
    Long statisticTypeId3;
    Long statisticTypeId4;
    Long statisticTypeId5;
    String ticketCateStatisticsName;
    String ticketCateStatisticsCode;
    String ticketCateStatisticsCode1;
    String ticketCateStatisticsCode2;
    String ticketCateStatisticsCode3;
    String ticketCateStatisticsCode4;
    String ticketCateStatisticsCode5;
    String cateStatisticNameParent;
    String cateStatisticNameParent1;
    String cateStatisticNameParent2;
    String cateStatisticNameParent3;
    String cateStatisticNameParent4;
    String cateStatisticNameParent5;
    Long status1;
    Long status2;
    Long status3;
    Long status4;
    Long status5;
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
    Date formDate;
    Date toDate;
    List<Long> lstIdsActive;
    List<Long> lstIdsInactive;
    String description;
    String template;
    Long parentId;
    Long parentId1;
    Long parentId2;
    Long parentId3;
    Long parentId4;
    List<Long> lstParentId;
    Long search;
    Long levelStatistic;
    Long ticketStatisticsLevelOne;
    Long ticketStatisticsLevelTwo;
    Long ticketStatisticsLevelThree;
    Long ticketStatisticsLevelFour;
    List<String> statusType;
//    Long levelTt;
}
