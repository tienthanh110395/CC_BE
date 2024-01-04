package com.viettel.etc.services;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketErrorCauseSearchDTO;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.utils.exceptions.EtcException;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class: Lop thao danh muc nguyen nhan loi
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
public interface TicketErrorCauseService {


    public Object getTicketErrorCause(TicketErrorCauseDTO itemParamsEntity);

    Object searchTicketErrorCause(TicketErrorCauseDTO itemParamsEntity, Authentication authentication);

    Object searchTreeTicketErrorCause(TicketErrorCauseDTO itemParamsEntity, Authentication authentication);

    Object removeTicketErrorCause(Long ticketErrorCauseId, Authentication authentication);

    Object updateTicketErrorCause(TicketErrorCauseDTO dataParams, Long ticketErrorCauseId, Authentication authentication) throws EtcException;

    Object createTicketErrorCause(TicketErrorCauseDTO dataParams, Authentication authentication);

    Object searchTicketErrorCauseNew(Authentication authentication, TicketErrorCauseSearchDTO params);

    Object getTicketErrorCauseById(TicketErrorCauseNewDTO params, Authentication authentication);

    Boolean onDelete(Long ticketErrorCauseId, Authentication authentication);

    boolean changeStatusTicketErrorCause(TicketErrorCauseNewDTO dataParams,Authentication authentication);

    boolean changeStatusMultipleTicketErrorCause(TicketErrorCauseNewDTO dataParams, Authentication authentication);

    Object createTicketErrorCauseNew(TicketErrorCauseNewDTO dataParams, Authentication authentication);

    Object getDataDetailTicketErrorCause(Long ticketErrorCauseId, TicketErrorCauseNewDTO params);

    List<TicketErrorCauseEntity> getErrorCauseByParentId(List<Long> lstParentId,Long levelId);
}
