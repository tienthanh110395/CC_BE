package com.viettel.etc.services;

import com.viettel.etc.dto.TicketInfoDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:05 ICT 2021
 */
public interface TicketStatisticTypeService {
    Object getTicketStatisticType(TicketStatisticTypeDTO itemParamsEntity, Authentication authentication);

    Object getTicketStatisticTypeDetail(Long parentId);
}