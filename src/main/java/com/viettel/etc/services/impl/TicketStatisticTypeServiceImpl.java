package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketInfoDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketStatisticTypeRepository;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.services.TicketStatisticTypeService;
import com.viettel.etc.services.tables.StatisticTypeServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:05 ICT 2021
 */
@Service
public class TicketStatisticTypeServiceImpl implements TicketStatisticTypeService{

    @Autowired 
    private TicketStatisticTypeRepository ticketStatisticTypeRepository;

    @Autowired
    StatisticTypeServiceJPA statisticTypeServiceJPA;


    /**
     * Lay thong tin loai thong ke
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketStatisticType(TicketStatisticTypeDTO itemParamsEntity, Authentication authentication) {
        return ticketStatisticTypeRepository.getTicketStatisticType(itemParamsEntity, authentication);
    }

    /**
     * Lay chi tiet loai thong ke
     *
     */
    @Override
    public Object getTicketStatisticTypeDetail(Long parentId) {
        StatisticTypeEntity statisticType = statisticTypeServiceJPA.getOne(parentId);
        return new TicketStatisticTypeDTO(statisticType);
    }
}