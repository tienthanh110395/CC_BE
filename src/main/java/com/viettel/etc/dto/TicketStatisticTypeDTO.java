package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;

import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Autogen class DTO: 
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:03 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketStatisticTypeDTO {

    Long statisticTypeId;

    String name;

    String code;

    String description;

    Long parentId;

    Long status;

    String createUser;

    Date createDate;

    String updateUser;

    Date updateDate;

    String myccId;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    public TicketStatisticTypeDTO(StatisticTypeEntity statisticType) {
        if (statisticType != null) {
            statisticTypeId = statisticType.getStatisticTypeId();
            code = statisticType.getCode();
            name = statisticType.getName();
            status = statisticType.getStatus();
            parentId = statisticType.getParentId();
            description = statisticType.getDescription();
            createDate = statisticType.getCreateDate();
            createUser = statisticType.getCreateUser();
            updateDate = statisticType.getUpdateDate();
            updateUser = statisticType.getUpdateUser();
            myccId = statisticType.getMyccId();
        }
    }
}