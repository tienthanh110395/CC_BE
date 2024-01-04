package com.viettel.etc.services;

import com.viettel.etc.dto.TicketAdjustChargeDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac tim kiem ticket
 *
 * @author ToolGen
 * @date Wed Mar 03 10:22:47 ICT 2021
 */
public interface TicketAdjustChargeService {
    Object saveTicketAdjustCharge(TicketAdjustChargeDTO itemParamsEntity, Authentication authentication);

    Object getTicketAdjustCharge(TicketAdjustChargeDTO itemParamsEntity, Authentication authentication);
}
