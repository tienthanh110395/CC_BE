package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ActionAuditSearchDTO  implements Serializable {

    private static final long serialVersionUID = 7703094822139881447L;

    Date fromDate;
    Date toDate;
    Date createDate;
    String updateUser;
    Integer startRecord;
    Integer pageSize;
    Long actType;
    String impactType;
    String description;
}
