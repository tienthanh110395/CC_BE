package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac lay lich su trang thai
 *
 * @author toolGen
 * @date Thu Mar 25 09:03:53 ICT 2021
 */
public interface TicketStatusRepository {
    ResultSelectEntity getTicketStatus(TicketStatusDTO itemParamsEntity, Authentication authentication);
}
