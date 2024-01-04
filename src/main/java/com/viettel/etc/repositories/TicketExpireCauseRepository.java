package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class Repository Interface: Lop thao danh muc nguyen nhan qua han
 *
 * @author toolGen
 * @date Thu Jun 03 11:31:38 ICT 2021
 */
public interface TicketExpireCauseRepository {
    ResultSelectEntity searchTicketExpireCause(TicketExpireCauseDTO itemParamsEntity);

    ResultSelectEntity searchTreeTicketExpireCause(TicketExpireCauseDTO itemParamsEntity);

    Object getListExpireCause(Authentication authentication, TicketExpireCauseSearchDTO params);

    ResultSelectEntity getExpireLevelByParent(TicketExpireCauseDTO dataParams);

    Object getDataDetailById(Long ticketExpireCauseId, TicketExpireCauseNewDTO params);
}
