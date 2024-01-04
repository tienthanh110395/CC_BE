package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.repositories.tables.entities.TicketEntity;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Autogen class DTO: Lop thao tac them moi ticket
 * 
 * @author ToolGen
 * @date Tue Mar 02 14:49:44 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketDTO {
    Long ticketId;
    @NotNull
    Long custId;
    @NotNull
    Long contractId;
    @NotEmpty
    String contractNo;
    Long custTypeId;
    String plateNumber;
    String phoneNumber;
    String custName;
    String email;
    String custAddress;
    @NotNull
    Long priorityId;
    Long sourceId;
    Long l1TicketTypeId;
    Long l2TicketTypeId;
    Long l3TicketTypeId;
    String l1TicketTypeName;
    String l2TicketTypeName;
    String l3TicketTypeName;
    String location;
    String areaCode;
    @NotEmpty
    String contentReceive;
    Long ticketKind;
    String supportInfo;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date requestDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date slaDate;

    String note;
    Long status;
    String statusName;
    Long responseStatus;
    Date reopenDate;
    Long assignType;
    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date processTime;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDateReport;

    String plateTypeCode;
    List<FileDTO> attachmentFiles;
    Long siteId;
    String provinceName;
    String districtName;
    String communeName;
    String phoneContact;
    Long sla;
    Long stageId;
    Long stationId;
    String stageName;
    String stationName;
    Long ticketTimes;
    String feedBack;

    @NotNull
    Long ticketChannel;

    String staffName;
    String processContent;
    String fileName;
    String attachmentId;

    @NotEmpty
    String otp;

    String ticketIds;
    String contractNos;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date fromDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date toDate;

    Integer startrecord;
    Integer pagesize;

    Long actTypeId;

    String inDueDate;

    String outOfDate;

    String closeQuantity;

    String sourceName;

    String processUser;

    String staffCode;

    String processUsers;

    public ActionAuditDTO toActionAuditDTO(){
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(actTypeId);
        actionAuditDTO.setContractId(contractId);
        actionAuditDTO.setTicketId(ticketId);
        return actionAuditDTO;
    }
}
