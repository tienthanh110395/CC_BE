package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class Repository Interface: Lop thao tac them moi ticket
 *
 * @author toolGen
 * @date Tue Mar 02 14:49:46 ICT 2021
 */
public interface TicketRepository {
    ResultSelectEntity getTicketInfo(TicketInfoDTO itemParamsEntity);

    ResultSelectEntity getTicketHistory(TicketHistoryDTO itemParamsEntity);

    ResultSelectEntity getListTicketHistories(TicketHistoryListDTO itemParamsEntity);

    ResultSelectEntity searchTicket(SearchTicketDTO itemParamsEntity);

    List<TicketDTO> getTicket(TicketDTO itemParamsEntity, Authentication authentication);

    ResultSelectEntity getTicketNotAssign(SearchTicketDTO itemParamsEntity, Authentication authentication);

    ResultSelectEntity getTicketReportPerformmance(SearchTicketDTO itemParamsEntity, Authentication authentication);
}
