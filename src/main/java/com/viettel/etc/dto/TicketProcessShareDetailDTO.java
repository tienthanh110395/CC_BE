package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import com.fasterxml.jackson.annotation.JsonInclude.Include;import lombok.Data;import lombok.NoArgsConstructor;import java.util.Date;/** * Autogen class DTO: * * @author ToolGen * @date Tue Aug 31 13:50:48 ICT 2021 */@Data@NoArgsConstructor@JsonInclude(Include.NON_NULL)public class TicketProcessShareDetailDTO {    Long ticketProcessShareDetailId;    Long ticketProcessShareId;    Date assignTime;    Long ticketId;    String processUser;    Integer startrecord;    Integer pagesize;}