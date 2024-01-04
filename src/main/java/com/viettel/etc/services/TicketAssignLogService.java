package com.viettel.etc.services;

import com.viettel.etc.dto.TicketAssignLogDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac tim assign log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
public interface TicketAssignLogService {
    Object getTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication);

    Object saveTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication);

    Object removeTicketAssignLog(Long ticketAssignLogId, Authentication authentication);

}
