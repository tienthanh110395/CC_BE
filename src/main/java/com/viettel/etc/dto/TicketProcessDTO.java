package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * Autogen class DTO: Lop thao tac tien xu ly cua CSKH
 * 
 * @author ToolGen
 * @date Tue Mar 02 16:00:41 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketProcessDTO {
    Long ticketProcessId;
    Long ticketId;
    String destroyReason;
    String processResult;
    @JsonFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date processTime;
    Long status;
    String reasonLevel1;
    String reasonLevel2;
    String processContent;
    String staffCode;
    String staffName;
    String createUser;
    Date createDate;
    String updateUser;
    Date updateDate;
    String phoneNumber;
    List<FileDTO> attachmentFiles;
    Long siteId;
    Boolean resultSqlEx;
    Long l1TicketTypeId;
    Long l2TicketTypeId;
    Long l3TicketTypeId;
    String groupPA;
    String subgroupPA;
    String detailPA;
    Long priorityId;
    List<TicketAttachmentEntity> listFiles;
    Long sourceId;
    Long ticketErrorCauseIdL1;
    Long ticketErrorCauseIdL2;
    Long ticketErrorCauseIdL3;
    String reasonLevel3;
    Long ticketExpireCauseIdL1;
    Long ticketExpireCauseIdL2;
    Long ticketExpireCauseIdL3;
    String ticketExpireCauseNameL1;
    String ticketExpireCauseNameL2;
    String ticketExpireCauseNameL3;
    Long ticketExpireSiteId;
    Long ticketSiteIdL1;
    Long ticketSiteIdL2;
    Long ticketSiteIdL3;
    Long actTypeId;
    String email;
    @JsonFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date slaDate;
    Integer startrecord;
    Integer pagesize;
}
