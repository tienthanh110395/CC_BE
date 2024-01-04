package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketStatisticDTO;
import java.util.List;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Thu Dec 02 09:01:14 ICT 2021
 */
public interface TicketStatisticRepository {

    ResultSelectEntity getTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication);
}