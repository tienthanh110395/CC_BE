package com.viettel.etc.services;

import com.viettel.etc.dto.TicketStatusDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac lay lich su trang thai
 *
 * @author ToolGen
 * @date Thu Mar 25 09:03:53 ICT 2021
 */
public interface TicketStatusService {
    Object getTicketStatus(TicketStatusDTO itemParamsEntity, Authentication authentication);
}
