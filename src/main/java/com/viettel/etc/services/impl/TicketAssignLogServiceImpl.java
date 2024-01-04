package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.repositories.TicketAssignLogRepository;
import com.viettel.etc.repositories.tables.TicketAssignLogRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignLogEntity;
import com.viettel.etc.services.TicketAssignLogService;
import com.viettel.etc.utils.FnCommon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop thao tac tim assign log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
@Service
public class TicketAssignLogServiceImpl implements TicketAssignLogService {

    @Autowired
    TicketAssignLogRepository ticketAssignLogRepository;

    @Autowired
    TicketAssignLogRepositoryJPA ticketAssignLogRepositoryJPA;


    /**
     * Lay thong tin ghi nhan qua trinh ho tro cua 1 phan anh
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication) {
        return ticketAssignLogRepository.getTicketAssignLog(itemParamsEntity, authentication);
    }

    /***
     * Them moi danh gia ghi nhan ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object saveTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication) {
        ModelMapper mapper = new ModelMapper();
        TicketAssignLogEntity ticketAssignLogEntity = mapper.map(itemParamsEntity, TicketAssignLogEntity.class);
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketAssignLogId())) {
            ticketAssignLogEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketAssignLogEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
        } else {
            ticketAssignLogEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketAssignLogEntity.setCreateUser(FnCommon.getUserLogin(authentication));
        }
        return ticketAssignLogRepositoryJPA.save(ticketAssignLogEntity);
    }

    /***
     * Xoa 1 ghi nhan ho tro
     * @param ticketAssignLogId
     * @return
     */
    @Override
    public Object removeTicketAssignLog(Long ticketAssignLogId, Authentication authentication) {
        ticketAssignLogRepositoryJPA.deleteById(ticketAssignLogId);
        return true;
    }
}
