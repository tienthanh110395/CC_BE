package com.viettel.etc.services;

import com.viettel.etc.dto.TicketAssignProcessDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Tue Mar 02 11:15:25 ICT 2021
 */
public interface TicketAssignProcessService {
    Object saveTicketAssignProcess(TicketAssignProcessDTO itemParamsEntity, Authentication authentication);

    Object getTicketAssignProcess(TicketStatusDTO itemParamsEntity, Authentication authentication);

}
