package com.viettel.etc.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Autogen class DTO: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:16 ICT 2022
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TicketExtentReasonDTO {

    Long ticketExtentReasonId;

    String name;

    String description;

    Long status;

    String createUser;

    Date createDate;

    String updateUser;

    Date updateDate;

    Integer startrecord;

    Integer pagesize;

    Boolean resultSqlEx;

}