package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketStatisticTypeDTO;
import java.util.List;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Wed Dec 01 13:45:05 ICT 2021
 */
public interface TicketStatisticTypeRepository {

    ResultSelectEntity getTicketStatisticType(TicketStatisticTypeDTO itemParamsEntity, Authentication authentication);
}