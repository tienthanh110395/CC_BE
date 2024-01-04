package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketCateStatisticSearchDTO {
    private static final long serialVersionUID = 7703094822139881447L;

    Long statisticTypeId;
    Date fromDate;
    Date toDate;
    boolean inEffect;
    boolean expire;
    List<Long> status;
    String createUser;
    Date createDate;
    String updateUser;
    Integer startRecord;
    Integer pageSize;
    List<Long> lstTicketCateStatisticsNameLv1Id;
    List<Long> lstTicketCateStatisticsNameLv2Id;
    List<Long> lstCateStatisticsLv1;
    List<Long> lstCateStatisticsLv2;
    List<Long> lstCateStatisticsLv3;
    List<Long> lstCateStatisticsLv4;
    List<Long> lstCateStatisticsLv5;
    List<Long> lstLevelStatistics;
}
