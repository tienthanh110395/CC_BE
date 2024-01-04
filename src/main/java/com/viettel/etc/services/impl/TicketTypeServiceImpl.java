package com.viettel.etc.services.impl;

import com.viettel.etc.repositories.TicketTypeRepository;
import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.TicketTypeService;
import com.viettel.etc.services.tables.TicketTypeServiceJPA;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Sun Jan 23 17:12:57 ICT 2022
 */
@Service
public class TicketTypeServiceImpl implements TicketTypeService{

    @Autowired 
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    TicketTypeServiceJPA ticketTypeServiceJPA;

    @Autowired
    TicketTypeRepositoryJPA ticketTypeRepositoryJPA;

    /**
     * Lay danh sach danh muc nhom, the loai, loai PA
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketType(TicketTypeDTO itemParamsEntity, Authentication authentication) {
        return ticketTypeRepository.getTicketType(itemParamsEntity, authentication);
    }

    /**
     * Lay chi tiet danh muc nhom, the loai, loai PA
     *
     */
    @Override
    public Object getTicketTypeDetails(Long ticketTypeId) {
        TicketTypeEntity ticketTypeEntity = ticketTypeServiceJPA.getOne(ticketTypeId);
        return new TicketTypeDTO(ticketTypeEntity);
    }

    /**
     * Tao moi danh muc nhom, the loai, loai PA
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @Override
    public Object createTicketType(TicketTypeDTO dataParams, Authentication authentication) {
        TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setName(StringUtils.trim(dataParams.getName()));
        ticketTypeEntity.setCode(dataParams.getCode());

        ticketTypeEntity.setIsCPT(dataParams.getIsCpt());
        ticketTypeEntity.setDescription(StringUtils.trim(dataParams.getDescription()));
        ticketTypeEntity.setStatus(dataParams.getStatus());
        ticketTypeEntity.setTicketTemplate(StringUtils.trim(dataParams.getTicketTemplate()));
        ticketTypeServiceJPA.save(ticketTypeEntity);
        return ticketTypeEntity;
    }

    /**
     * Sua danh muc nhom, the loai, loai PA
     *
     * @param dataParams     params client
     * @param ticketTypeId
     * @param authentication : thong tin nguoi dung
     * @return
     */
    @Override
    public Object updateTicketType(TicketTypeDTO dataParams, Long ticketTypeId, Authentication authentication) throws EtcException {
        TicketTypeEntity ticketType = ticketTypeServiceJPA.getOne(ticketTypeId);
        if (ticketType == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }
        ticketType.setName(StringUtils.trim(dataParams.getName()));
        ticketType.setCode(dataParams.getCode());

        ticketType.setIsCPT(dataParams.getIsCpt());
        ticketType.setDescription(StringUtils.trim(dataParams.getDescription()));
        ticketType.setStatus(dataParams.getStatus());
        ticketType.setTicketTemplate(StringUtils.trim(dataParams.getTicketTemplate()));
        TicketTypeEntity savedTicketTypes = ticketTypeServiceJPA.save(ticketType);
        return savedTicketTypes;
    }

    /***
     * Xóa 1 bản ghi danh muc nhom, the loai, loai PA
     * @param ticketTypeId
     * @return
     */
    @Override
    public Boolean removeTicketType(Long ticketTypeId, Authentication authentication) {
        ticketTypeRepositoryJPA.deleteById(ticketTypeId);
        return true;
    }
}