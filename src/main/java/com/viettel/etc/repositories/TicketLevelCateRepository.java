package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketLevelCateRepository {
    Object getDataDetail(Long ticketLevelCateId, TicketLevelCateDTO params);

    Object searchTicketLevelCate(Authentication authentication, TicketLevelCateSearchDTO params);

    ResultSelectEntity getTicketProcessTime(Authentication authentication);

    ResultSelectEntity getTicketLevelCateNameById(TicketLevelCateDTO params);


}
