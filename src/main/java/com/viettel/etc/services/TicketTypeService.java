package com.viettel.etc.services;

import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Sun Jan 23 17:12:57 ICT 2022
 */
public interface TicketTypeService {

    Object getTicketType(TicketTypeDTO itemParamsEntity, Authentication authentication);

    Object getTicketTypeDetails(Long ticketTypeId);

    Object createTicketType(TicketTypeDTO dataParams, Authentication authentication);

    Object updateTicketType(TicketTypeDTO dataParams, Long ticketTypeId, Authentication authentication) throws EtcException;

    Object removeTicketType(Long ticketTypeId, Authentication authentication);
}