package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketInfoDTO {
    String ticketId;
    Long contractId;
    String plateNumber;
    String custId;
    String custName;
    String custTypeId;
    String phoneNumber;
    String email;
    String status;
    String sourceId;
    String sourceName;
    String custAddress;
    String contentReceive;
    String contractNo;
    List<TicketAttachmentEntity>  ticketAttachmentEntityList;

    Long actTypeId;
    Long l1TicketTypeId;
    Long l2TicketTypeId;
    Long l3TicketTypeId;
    Long stageId;
    Long stationId;
    String stageName;
    String stationName;
    String provinceName;
    String districtName;
    String communeName;
    String phoneContact;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date requestDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date slaDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    String updateUser;
    Long priorityId;
    Long ticketKind;
    String supportInfo;
    String note;
    List<FileDTO> attachmentFiles;

    public ActionAuditDTO toActionAuditDTO(){
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(actTypeId);
        actionAuditDTO.setContractId(contractId);
        actionAuditDTO.setTicketId(Long.valueOf(ticketId));
        return actionAuditDTO;
    }
}
