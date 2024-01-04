package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ThaiBQ
 * @date   07/06/2023
 */

@Data
@NoArgsConstructor
public class MapErrorCauseSearchDTO implements Serializable {

    private static final long serialVersionUID = 7703094822139881447L;

    Date formDate;
    Date toDate;
    String createUser;
    Integer startRecord;
    Integer pageSize;
    List<Long> lstTicketTypeGroupId;
    List<Long> lstTicketCategoryId;
    List<Long> lstErrorCauseLv1;
    List<Long> lstErrorCauseLv2;
    List<Long> lstErrorCauseLv3;
    List<Long> lstParentLvId2;
    List<Long> lstParentLvId3;
}
