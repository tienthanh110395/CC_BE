package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketErrorCauseSearchDTO;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class Repository Interface: Lop thao danh muc nguyen nhan loi
 *
 * @author toolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
public interface TicketErrorCauseRepository {
    ResultSelectEntity searchTicketErrorCause(TicketErrorCauseDTO itemParamsEntity);

    ResultSelectEntity searchTreeTicketErrorCause(TicketErrorCauseDTO itemParamsEntity);

    ResultSelectEntity getTicketErrorCauseByParent( TicketErrorCauseNewDTO params);

    Object searchTicketErrorCauseNew(Authentication authentication, TicketErrorCauseSearchDTO params);

    Object getDataDetailTicketErrorCause(Long ticketErrorCauseId, TicketErrorCauseNewDTO params);

    List<TicketErrorCauseEntity> getErrorCauseByParentId(List<Long> lstParentId, Long levelId);

}
