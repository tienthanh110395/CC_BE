package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketConfigSearchDTO implements Serializable {
    private static final long serialVersionUID = 7703094822139881447L;

    String ticketTypeName;
    String ticketTypeCode;
    boolean inEffect;
    boolean expire;
    Date formDate;
    Date toDate;
    String createUser;
    List<Long> lstTicketTypeGroupId;
    List<Long> lstTicketTypeGenreId;
    List<Long> lstTicketCategoryId;
    Integer startRecord;
    Integer pageSize;
    Long ticketTypeLevel;
    List<Long> status;
    List<Long> lstTicketType;
}
