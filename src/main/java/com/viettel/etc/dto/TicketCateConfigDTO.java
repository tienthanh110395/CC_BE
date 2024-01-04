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
public class TicketCateConfigDTO {
    Long ticketTypeId;
    String ticketTypeName;
    String ticketTypeCode;
    String description;
    Long parentId;
    List<Long> lstParentId;
    Long status;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    Long isCpt;
    Long type;
    String ticketTemplate;
    Long deadTimeType;
    Integer startRecord;
    Integer pageSize;
    String ticketGenre;
    String ticketGroup;
    Long levelTt;
    String formDate;
    String toDate;
    Long ticketTypeGroupId;
    List<Long> lstIdsActive;
    List<Long> lstIdsInactive;
    List<Long> lstIds;
    Long search;
    String name;
}
