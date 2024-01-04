package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class TicketTypeLogDetailDTO {
    Long ticketTypeLogDetailId;
    Long ticketTypeLogId;
    Long tableId;
    String tableName;
    String createUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String oldValue;
    String newValue;
    String columnName;
    Integer startRecord;
    Integer pageSize;
}
