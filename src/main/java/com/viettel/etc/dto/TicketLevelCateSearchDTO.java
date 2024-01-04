package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class TicketLevelCateSearchDTO implements Serializable {
    private static final long serialVersionUID = 7703094822139881447L;

    String ticketLevelCateCode;

//    String ticketLevelCateName;

    Date fromDate;

    Date toDate;

    List<Long> status;

    Boolean inEffect;

    String createUser;

    Boolean expire;

    Integer startRecord;

    Integer pageSize;

    List<Long> lstLevelCateId;

}
