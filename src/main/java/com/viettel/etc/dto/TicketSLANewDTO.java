package com.viettel.etc.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.OtherCategoriesEntity;
import com.viettel.etc.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketSLANewDTO {

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

    Boolean resultSqlEx;

    Long parentId;

    String ticketGroupName;
    String ticketGroupCode;
    String ticketGenreName;
    String ticketGenreCode;
    String ticketTypeName;
    String ticketTypeCode;
    String priorityName;

    Long ticketGroupId;
    Long ticketGenreId;

    String receptionTimeFrom;
    String receptionTimeTo;
    Long reTicketTime;

    List<Long> lstTicketType;
    List<TicketLevelCateDTO> lstDataTicketTimeConfig;

    Date fromDate;
    Date toDate;

    String ticketGroupStatus;
    String ticketGenreStatus;
    String ticketTypeStatus;
    String ticketTemplate;
    String priorityCode;
}
