package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;

import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * Autogen class DTO: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:06 ICT 2022
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketExtentDTO {

    Long ticketExtentId;

    Long ticketId;

    @NotNull
    List<Long> ticketIds;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date extentDate;

    Long extentReasonId;

    String extentReasonCode;

    String extentReasonName;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date requestExtentDate;

    String managerUserName;

    String managerPhone;

    Long status;

    String approveUserName;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date approveDate;

    String approveReason;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    String updateUser;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

    Boolean isSms;

    Long actTypeId;

    String toSiteId;
    String contractNo;
    String groupPA;
    String subgroupPA;
    String detailPA;
    String contentReceive;
    String siteName;
    String ticketSearchList;
    Long l1TicketTypeId;
    Long l2TicketTypeId;
    Long l3TicketTypeId;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date deadlineProcess;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date startExtentDate;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date endExtentDate;

    public TicketSmsMailPushEntity toTicketSmsMailPushEntity(Long ticketId, String content, String phone) {
        TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketId(ticketId);
        ticketSmsMailPushEntity.setSmsMailPushType(TicketSmsMailPushEntity.SMSMailPushType.SEND_SMS.value);
        ticketSmsMailPushEntity.setMessage(content);
        ticketSmsMailPushEntity.setPhone(phone);
        ticketSmsMailPushEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketSmsMailPushEntity.setCreateUser(managerUserName);
        return ticketSmsMailPushEntity;
    }

    public TicketExtentDTO(TicketExtentEntity ticketExtentEntity) {
        if (ticketExtentEntity != null) {
            approveReason = ticketExtentEntity.getApproveReason();
            ticketId = ticketExtentEntity.getTicketId();
            extentReasonId = ticketExtentEntity.getExtentReasonId();
            managerUserName = ticketExtentEntity.getManagerUserName();
            managerPhone = ticketExtentEntity.getManagerPhone();
            requestExtentDate = ticketExtentEntity.getRequestExtentDate();
            status = ticketExtentEntity.getStatus();
        }
    }

}