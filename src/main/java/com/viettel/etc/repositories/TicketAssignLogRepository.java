package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac tim assign log
 *
 * @author toolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
public interface TicketAssignLogRepository {
    ResultSelectEntity getTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication);
}
