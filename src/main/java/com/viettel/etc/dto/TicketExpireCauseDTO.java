package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Autogen class DTO: Lop thao danh muc nguyen nhan qua han
 * 
 * @author ToolGen
 * @date Thu Jun 03 11:31:36 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketExpireCauseDTO {
    Long ticketExpireCauseId;

    String name;

    String code;

    String description;

    Long parentId;

    Long status;

    String createUser;

    List<Long> lstParentId;

    Long search;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    Integer startrecord;

    Integer pagesize;

    List<String> statusType;

    Long ticketExpireCauseId2;

    String name2;

    String code2;

    Long parentId2;

    Long ticketExpireCauseId3;

    String name3;

    String code3;

    Long parentId3;
}
