package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.repositories.TicketSiteUserRepository;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.services.TicketSiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop thao tac ticket site user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
@Service
public class TicketSiteUserServiceImpl implements TicketSiteUserService {

    @Autowired
    TicketSiteUserRepositoryJPA ticketSiteUserRepositoryJPA;

    @Autowired
    TicketSiteUserRepository ticketSiteUserRepository;


    /**
     * Lay id phong ban theo user dang nhap
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getOneTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication) {
        return ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase(itemParamsEntity.getUserName());
    }


    /**
     *
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication) {
        return ticketSiteUserRepository.getTicketSiteUser(itemParamsEntity, authentication);
    }
}
