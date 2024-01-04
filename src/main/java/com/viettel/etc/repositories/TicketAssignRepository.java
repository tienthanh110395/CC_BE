package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author toolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */
public interface TicketAssignRepository {
    ResultSelectEntity getTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication);

    ResultSelectEntity getTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication);
}
