package com.viettel.etc.repositories;

import com.viettel.etc.dto.TicketCateStatisticDTO;
import com.viettel.etc.dto.TicketCateStatisticSearchDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketTypeLogDTO;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketCateStatisticRepository {
    Object searchTicketCateStatistic(Authentication authentication, TicketCateStatisticSearchDTO params);

    ResultSelectEntity getTicketCateStatisticByParent(TicketCateStatisticDTO params);

    Object getDataDetailTicketCateStatistic(Long statisticTypeId, TicketCateStatisticDTO params);
    ResultSelectEntity exportFile(TicketCateStatisticSearchDTO params);
}
