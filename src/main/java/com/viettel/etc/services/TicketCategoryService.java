package com.viettel.etc.services;

import com.viettel.etc.dto.*;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac  danh sach cac danh muc
 *
 * @author ToolGen
 * @date Tue Mar 02 14:25:37 ICT 2021
 */
public interface TicketCategoryService {
    Object getTicketSource(TicketSourceDTO itemParamsEntity);

    Object getTicketTypes(TicketTypeDTO params, Authentication authentication);

    Object getTicketSiteByParentId(TicketSiteDTO params);

    Object getTicketSla(TicketSLADTO params);

    Object getTicketSlaDetail(TicketSLADTO params);

    Object getTicketTypesTree(TicketTypeDTO params);

    Object saveTicketSla(TicketSLADTO ticketSLADTO, Authentication authentication);

    java.util.Date getDateTicketSlaNew(Long priorityId, Long ticketTypeId, Authentication authentication, Long processTime);

    Object getTicketSiteUser(TicketSiteUserDTO params, Authentication authentication);

    Object getTicketExtentReason(TicketExtentReasonDTO params, Authentication authentication);
}
