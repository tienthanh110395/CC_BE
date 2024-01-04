package com.viettel.etc.services;

import com.viettel.etc.dto.*;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class: Lop thao danh muc nguyen nhan qua han
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:38 ICT 2021
 */
public interface TicketExpireCauseService {

    Object getTicketExpireCause(TicketExpireCauseDTO itemParamsEntity);

    Object searchTicketExpireCause(TicketExpireCauseDTO itemParamsEntity, Authentication authentication);

    Object searchTreeTicketExpireCause(TicketExpireCauseDTO itemParamsEntity, Authentication authentication);

    Object createTicketExpireCause(TicketExpireCauseDTO dataParams, Authentication authentication);

    Object updateTicketExpireCause(TicketExpireCauseDTO dataParams, Long ticketExpireCauseId, Authentication authentication) throws EtcException;

    Object removeTicketExpireCause(Long ticketExpireCauseId, Authentication authentication);

    Object getListDataExpireCause(Authentication authentication, TicketExpireCauseSearchDTO params);

    Object getExpireLevelByParentId(TicketExpireCauseDTO dataParams, Authentication authentication);

    Object createOrUpdate(TicketExpireCauseNewDTO params, Authentication authentication);

    Boolean doDeleteData(Long ticketExpireCauseId, Authentication authentication);

    Object getDataDetail(Long ticketExpireCauseId, TicketExpireCauseNewDTO params);

    Object changeStatus(TicketExpireCauseNewDTO params, Authentication authentication);

    Object updateStatusMultiple(TicketExpireCauseNewDTO params, Authentication authentication);
}
