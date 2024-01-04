package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketSiteSearchDTO implements Serializable {
    private static final long serialVersionUID = 7703094822139881210L;

    Long ticketSiteId;
    String ticketSiteCode;
    String ticketSiteName;
    Long parentId;
    String createUser;
    Integer startRecord;
    Integer pageSize;
    boolean inEffect;
    boolean expire;
    Date formDate;
    Date toDate;
    List<Long> status;
    Long levelSite;
    List<Long> lstTicketSite;
    List<Long> lstSiteLv;
    String siteUser;

}
