package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketStatusRepository;
import com.viettel.etc.services.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop thao tac lay lich su trang thai
 *
 * @author ToolGen
 * @date Thu Mar 25 09:03:53 ICT 2021
 */
@Service
public class TicketStatusServiceImpl implements TicketStatusService {

    @Autowired
    TicketStatusRepository ticketStatusRepository;


    /**
     * Lay thong tin lich su trang thai phan anh
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketStatus(TicketStatusDTO itemParamsEntity, Authentication authentication) {
        return ticketStatusRepository.getTicketStatus(itemParamsEntity, authentication);
    }
}
