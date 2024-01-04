package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketAssignProcessIdDTO {
    String custId;
    String custName;
    String plateNumber;
    String plateTypeCode;
    String phoneNumber;
    String email;
    String contractNo;
    String custAddress;
    String priorityId;
    String ticketKind;
    String location;
    String areaCode;
    String contentReceive;
    String supportInfo;

    Date requestDate;
    String note;
    String sourceCode;
    String sourceName;
    String l1TicketTypeId;
    String groupPA;
    String l2TicketTypeId;
    String subgroupPA;
    String l3TicketTypeId;
    String detailPA;
    String assignContent;
    String ticketId;
    String ticketAssignId;
    String siteId;
    String siteName;
    String[] fileNames;
    String[] filePaths;
    Long custTypeId;
    String status;
    List<TicketAttachmentEntity> listAttachFiles;
    Long ticketAssignProcessId;
    Date createDate;
    String createUser;
    Date slaDate;
    String userProcess;
    Date processTime;
    Long sourceId;
    String toSiteId;
    String updateUser;
}
