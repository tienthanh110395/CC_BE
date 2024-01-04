package com.viettel.etc.services;

import com.viettel.etc.dto.*;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac them moi ticket
 *
 * @author ToolGen
 * @date Tue Mar 02 14:49:46 ICT 2021
 */
public interface TicketService {
    Object saveTicket(TicketDTO itemParamsEntity, Authentication authentication);

    Object getTicketDetails(TicketInfoDTO itemParamsEntity, Authentication authentication);

    Object getTicketHistory(TicketHistoryDTO itemParamsEntity);

    Object getListTicketHistories(TicketHistoryListDTO itemParamsEntity);

    Object exportHistoryTicket(TicketHistoryListDTO itemParamsEntity);

    Object searchTicket(SearchTicketDTO itemParamsEntity);

    Object exportTicket(SearchTicketDTO itemParamsEntity, Authentication authentication);

    Object updateTicket(TicketDTO itemParamsEntity, Authentication authentication);

    Object saveTicketForCPT(TicketDTO itemParamsEntity, Authentication authentication);

    Object getTicketForCPT(TicketDTO ticketDTO, Authentication authentication);

    Object getTicketNotAssign(SearchTicketDTO searchTicketDTO, Authentication authentication);

    Object exportTicketNotAssign(SearchTicketDTO searchTicketDTO, Authentication authentication);

    Object getTicketReportPerformmance(SearchTicketDTO searchTicketDTO, Authentication authentication);

    Object exportTicketReportPerformance(SearchTicketDTO searchTicketDTO, Authentication authentication);

    Object editTicket(TicketInfoDTO itemParamsEntity, Long ticketId,Authentication authentication) throws Exception;
}
