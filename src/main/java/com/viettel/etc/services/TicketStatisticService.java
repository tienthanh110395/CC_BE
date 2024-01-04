package com.viettel.etc.services;

import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:14 ICT 2021
 */
public interface TicketStatisticService {
    Object getTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication);

    Object exportTicketStatistic(TicketStatisticDTO dataParams, Authentication authentication);

    Object insertTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication);
}