package com.viettel.etc.services;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:08 ICT 2022
 */
public interface TicketExtentService {
    Object getTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication);

    Object insertTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication);

    Object getDetailTicketExtent(Long ticketId);

    Object updateTicketExtent(TicketExtentDTO dataParams, Long ticketId, Authentication authentication) throws EtcException;

    List<TicketExtentEntity> approveTicketStatus(TicketExtentDTO dataParams, Authentication authentication ) throws EtcException;

    Object rejectTicketStatus(TicketExtentDTO dataParams, Long ticketId, Authentication authentication ) throws EtcException;

    Object exportTicketExtent(TicketExtentDTO dataParams, Authentication authentication);

    Object exportTicketProcess(TicketAssignDTO dataParams, Authentication authentication);
}