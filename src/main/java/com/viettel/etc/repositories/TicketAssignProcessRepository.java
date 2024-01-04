package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author toolGen
 * @date Tue Mar 02 11:15:25 ICT 2021
 */
public interface TicketAssignProcessRepository {
    ResultSelectEntity getTicketAssignProcess(TicketStatusDTO params, Authentication authentication);
}
