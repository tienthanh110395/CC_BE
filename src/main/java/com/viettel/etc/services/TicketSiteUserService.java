package com.viettel.etc.services;

import com.viettel.etc.dto.TicketSiteUserDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac ticket site user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
public interface TicketSiteUserService {
    Object getOneTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication);

    Object getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication);
}
