package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class MapErrorCauseDTO {
    Long mapErrorCauseId;
    String ticketErrorName1;
    String ticketErrorName2;
    String ticketErrorName3;
    Long ticketErrorId1;
    Long ticketErrorId2;
    Long ticketErrorId3;
    Long ticketGenreId;
    String ticketGenreCode;
    String ticketGenre;
    String ticketGenreStatus;
    String ticketGroupCode;
    String ticketGroup;
    String ticketGroupStatus;
    Long ticketGroupId;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date createDate;
    String createUser;
    String updateUser;
    @DateTimeFormat(pattern = Constants.COMMON_DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.COMMON_DATE_TIME_FORMAT, locale = Constants.LOCALE_VN, timezone = Constants.TIMEZONE_VN)
    Date updateDate;
    Long parentIdLv2;
    Long parentIdLv3;
}
