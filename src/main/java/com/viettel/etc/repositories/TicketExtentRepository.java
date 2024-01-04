package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketExtentDTO;
import java.util.List;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Fri Jan 07 16:32:08 ICT 2022
 */
public interface TicketExtentRepository {
    ResultSelectEntity getTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication);
}