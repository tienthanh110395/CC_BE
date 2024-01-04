package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: Lop thao tac  danh sach cac danh muc
 *
 * @author toolGen
 * @date Tue Mar 02 14:25:37 ICT 2021
 */
public interface TicketCategoryRepository {
    ResultSelectEntity getTicketTypes(TicketTypeDTO itemParamsEntity, Authentication authentication);

    ResultSelectEntity getTicketSiteByParentId(TicketSiteDTO itemParamsEntity);

    ResultSelectEntity getTicketSla(TicketSLADTO itemParamsEntity);

    ResultSelectEntity getTicketSource(TicketSourceDTO itemParamsEntity);

    ResultSelectEntity getTicketTypesTree(TicketTypeDTO itemParamsEntity);

    ResultSelectEntity getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication);

    ResultSelectEntity getTicketExtentReason(TicketExtentReasonDTO itemParamsEntity, Authentication authentication);
}
