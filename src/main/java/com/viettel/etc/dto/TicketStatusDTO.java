package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Autogen class DTO: Lop thao tac lay lich su trang thai
 * 
 * @author ToolGen
 * @date Thu Mar 25 09:03:51 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketStatusDTO {
    Long ticketStatusId;

    Long ticketId;

    Long siteId;

    Long ticketStatus;

    Date processTime;

    String note;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;

    String processContent;

    String siteName;

    Integer startrecord;

    Integer pagesize;
}
