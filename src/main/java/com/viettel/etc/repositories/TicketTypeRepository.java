package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketSlaSearchDTO;
import com.viettel.etc.dto.TicketTypeDTO;
import java.util.List;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Sun Jan 23 17:12:57 ICT 2022
 */
public interface TicketTypeRepository {

    ResultSelectEntity getTicketType(TicketTypeDTO itemParamsEntity, Authentication authentication);

    List<String> getListTicketGroupImport(Long levelTT);
}