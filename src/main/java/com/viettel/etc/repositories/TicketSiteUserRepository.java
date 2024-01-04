package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac ticket site user
 *
 * @author toolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
public interface TicketSiteUserRepository {
    ResultSelectEntity getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication);
}
