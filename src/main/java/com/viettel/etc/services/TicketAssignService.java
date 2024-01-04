package com.viettel.etc.services;

import com.viettel.etc.dto.TicketAssignDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */
public interface TicketAssignService {
    Object getTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object exportTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object receiveTicketToHandle(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object getTicketAssignByTicketId(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object getTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object saveTicketAssign(TicketAssignDTO itemParamsEntity, Authentication authentication);

    Object removeTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication);

}
