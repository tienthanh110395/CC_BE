package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketSLADTO {

    Long ticketSlaId;

    String slaName;

    Long sla;

    String description;

    Long status;

    Long siteId;

    Long sourceId;

    Long ticketTypeId;

    Long priorityId;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    Long processTime;

    Long processTimeType;

    Long combineTimeL1;

    Long combineTimeL1Type;

    Long combineTimeL2;

    Long combineTimeL2Type;

    Long manTimeSla;

    Long isAddCombine;

    Long processType;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date slaDate;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    public TicketSLADTO(long l, String slaName, long l1, String description, long l2, long l3, long l4, long l5, long l6, String createUser, Date time, String updateUser, Date time1, long l7, long l8, long l9, long l10, long l11, long l12, long l13, long l14, long l15, Date time2) {
    }
}