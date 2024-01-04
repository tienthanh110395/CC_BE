package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketExpireCauseSearchDTO implements Serializable {
    private static final long serialVersionUID = 7703094822139881447L;

    boolean inEffect;
    boolean expire;
    Date formDate;
    Date toDate;
    List<Long> lstExpireCauseOne;
    List<Long> lstExpireCauseTwo;
    List<Long> lstExpireCauseThree;
    List<Long> lstLevelExpire;
    Integer startRecord;
    Integer pageSize;
    Long ticketTypeLevel;
    List<Long> status;
    String createUser;
}
