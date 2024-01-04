package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.viettel.etc.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketSLADetailDTO {

    Long ticketSlaId;
    Long ticketTypeId;

    Long priorityId;
    Long processTime;

    Long combineTimeL1;

    Long combineTimeL2;

    Long parentId;

    String ticketGroupName;
    String ticketGenreName;
    String ticketTypeName;
    String priorityName;

    Long ticketGroupId;
    Long ticketGenreId;

    String receptionTimeFrom;
    String receptionTimeTo;
    Long reTicketTime;
}
