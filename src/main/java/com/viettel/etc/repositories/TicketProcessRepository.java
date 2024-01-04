package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

/**
 * Autogen class Repository Interface: Lop thao tac tien xu ly cua CSKH
 *
 * @author toolGen
 * @date Tue Mar 02 16:00:43 ICT 2021
 */
public interface TicketProcessRepository {
    ResultSelectEntity getTicketProcessInfo(TicketProcessDTO ticketProcessDTO);
}
