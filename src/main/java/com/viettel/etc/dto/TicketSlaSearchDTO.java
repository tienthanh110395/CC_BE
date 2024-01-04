package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketSlaSearchDTO implements Serializable {
    private static final long serialVersionUID = 7703094822139881447L;

    Date formDate;
    Date toDate;
    String createUser;
    List<Long> lstTicketGroup;
    List<Long> lstTicketGenre;
    List<Long> lstTicketType;
    List<Long> lstPriority;
    Integer startRecord;
    Integer pageSize;
}
