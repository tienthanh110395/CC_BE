package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketErrorCauseSearchDTO implements Serializable {

    private static final long serialVersionUID = 7703094822139881447L;

    Long ticketErrorCauseId;
    Date formDate;
    Date toDate;
    boolean inEffect;
    boolean expire;
    List<Long> status;
    String createUser;
    Date createDate;
    String updateUser;
    Integer startRecord;
    Integer pageSize;
    List<Long> lstTicketErrorCauseNameLv1Id;
    List<Long> lstTicketErrorCauseNameLv2Id;
    List<Long> lstErrorCauseLv1;
    List<Long> lstErrorCauseLv2;
    List<Long> lstErrorCauseLv3;
    List<Long> lstLevelError;
}
