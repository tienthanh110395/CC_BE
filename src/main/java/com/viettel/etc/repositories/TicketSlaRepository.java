package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;

import java.util.List;

import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Wed Jan 26 09:59:24 ICT 2022
 */
public interface TicketSlaRepository {

    ResultSelectEntity getTicketSla(TicketSLADTO itemParamsEntity);

    ResultSelectEntity getTicketSlaDetail(Long ticketTypeId);

    Object getListTicketSLA(Authentication authentication, TicketSlaSearchDTO params);

    Object getDataDetailById(Long ticketTypeId, TicketSLANewDTO params);

    ResultSelectEntity exportImpact(TicketSlaSearchDTO params);

    int updateReptionTime(String receptionTimeFrom,String receptionTimeTo);
}