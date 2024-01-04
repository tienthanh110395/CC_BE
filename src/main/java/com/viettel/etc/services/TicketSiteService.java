package com.viettel.etc.services;

import com.viettel.etc.dto.TicketSiteConfigDTO;
import com.viettel.etc.dto.TicketSiteSearchDTO;
import com.viettel.etc.dto.TicketSiteUserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketSiteService {
    Object searchTicketSite(Authentication authentication, TicketSiteSearchDTO dto);

    Object changeStatus(TicketSiteConfigDTO dto, Authentication authentication);

    Object updateStatusMultiple(TicketSiteConfigDTO dto, Authentication authentication);

    Object getTicketSite(Authentication authentication);

    Object createUpdate(Authentication authentication, TicketSiteConfigDTO dto, TicketSiteUserDTO dtoSiteUser);

    Object onDelete(Long ticketSiteId);

    Object getDataDetail(Long ticketSiteId, TicketSiteConfigDTO dto);

    Object getAllUser(Authentication authentication);

    Object getAllSiteUser(Authentication authentication);

}
