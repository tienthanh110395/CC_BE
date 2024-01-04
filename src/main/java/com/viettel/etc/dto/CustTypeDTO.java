package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustTypeDTO {
    String custTypeId;
    String code;
    String name;
    Long type;
    String description;
    String status;
    Date createDate;
    Date updateDate;
    String createUser;
    String updateUser;
}
