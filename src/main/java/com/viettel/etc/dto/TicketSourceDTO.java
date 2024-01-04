package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketSourceDTO {
    Long ticketSourceId;

    String name;

    String sourceCode;

    String description;

    Long status;

    String createUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;

    String updateUser;

    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
}
