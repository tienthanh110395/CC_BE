package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketSiteConfigDTO;
import com.viettel.etc.dto.TicketSiteSearchDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketSiteRepository {
    Object searchTicketSite(Authentication authentication, TicketSiteSearchDTO dto);

    Object getTicketSite(Authentication authentication);

    Object getDataDetail(Long ticketSiteId, TicketSiteConfigDTO dto);

    ResultSelectEntity getLstUserHandle(Long ticketSiteId, TicketSiteConfigDTO dto);

}
