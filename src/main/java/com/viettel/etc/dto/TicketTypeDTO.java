package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketTypeDTO {
    Long ticketTypeId;

    String name;

    String code;

    String description;

    String parentId;

    Long status;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    Long level;

    Long type;

    String ticketTemplate;

    Long deadTimeType;

    Long hotSla;

    Long othersSla;


    Long isCpt;


    String myccId;


    Integer startrecord;


    Integer pagesize;

    String groupPA;

    String subgroupPA;

    String detailPA;

    Boolean resultSqlEx;

    List<String> statusReac;

    public TicketTypeDTO(TicketTypeEntity ticketTypeEntity) {
        if (ticketTypeEntity != null) {
            ticketTypeId = ticketTypeEntity.getTicketTypeId();
            parentId = String.valueOf(ticketTypeEntity.getParentId());
            name = ticketTypeEntity.getName();
            code = ticketTypeEntity.getCode();
            status = ticketTypeEntity.getStatus();
            description = ticketTypeEntity.getDescription();
            isCpt = ticketTypeEntity.getIsCPT();
            ticketTemplate = ticketTypeEntity.getTicketTemplate();
        }
    }
}