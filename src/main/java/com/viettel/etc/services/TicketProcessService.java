package com.viettel.etc.services;

import com.viettel.etc.dto.TicketProcessDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac tien xu ly cua CSKH
 *
 * @author ToolGen
 * @date Tue Mar 02 16:00:43 ICT 2021
 */
public interface TicketProcessService {

    Object saveTicketProcess(TicketProcessDTO itemParamsEntity, Authentication authentication);

    Object getTicketProcessDetails(TicketProcessDTO itemParamsEntity, Authentication authentication);
}
