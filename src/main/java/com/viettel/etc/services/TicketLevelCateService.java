package com.viettel.etc.services;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.dto.TicketLevelCateDTO;
import com.viettel.etc.dto.TicketLevelCateSearchDTO;
import org.springframework.security.core.Authentication;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TicketLevelCateService {

    boolean changeStatus(TicketLevelCateDTO dataParams, Authentication authentication);

    Object createUpdate(TicketLevelCateDTO params, Authentication authentication);

    Boolean onDelete(Long ticketLevelCateId, Authentication authentication);

    Object getDataDetail(Long ticketLevelCateId, TicketLevelCateDTO params);

    Object createUpdateList(List<TicketLevelCateDTO> params, Authentication authentication) throws Exception;


    Object searchTicketLevelCate(Authentication authentication, TicketLevelCateSearchDTO params);

    Object getListOtherCateConfigProcessTime(Authentication authentication);

    Object getTicketLevelCateNameById(TicketLevelCateDTO params, Authentication authentication);

}
