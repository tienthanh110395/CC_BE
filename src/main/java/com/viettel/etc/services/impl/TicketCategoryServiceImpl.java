package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.repositories.tables.TicketSlaRepositoryJPA;
import com.viettel.etc.repositories.tables.WorkingDaysRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import com.viettel.etc.repositories.tables.entities.TicketStatusEntity;
import com.viettel.etc.services.TicketCategoryService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

/**
 * Autogen class: Lop thao tac  danh sach cac danh muc
 *
 * @author ToolGen
 * @date Tue Mar 02 14:25:37 ICT 2021
 */
@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    @Autowired
    TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    TicketSlaRepositoryJPA ticketSlaRepositoryJPA;

    @Autowired
    WorkingDaysRepositoryJPA workingDaysRepositoryJPA;


    /**
     * Lay thong tin nguon phan anh
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketSource(TicketSourceDTO itemParamsEntity) {
        return ticketCategoryRepository.getTicketSource(itemParamsEntity);
    }

    /***
     * Lay thong tin loai phan anh
     * @param params
     * @return
     */
    @Override
    public Object getTicketTypes(TicketTypeDTO params, Authentication authentication) {
        return ticketCategoryRepository.getTicketTypes(params, authentication);
    }

    /***
     * Lay thong tin phong ban
     * @param params
     * @return
     */
    @Override
    public Object getTicketSiteByParentId(TicketSiteDTO params) {
        return ticketCategoryRepository.getTicketSiteByParentId(params);
    }

    /***
     * Lay thong tin thoi han xu ly phan anh
     * @param params
     * @return
     */
    @Override
    public Object getTicketSla(TicketSLADTO params) {
        return ticketCategoryRepository.getTicketSla(params);
    }

    /***
     * Lay thong tin thoi han xu ly 1 loai phan anh
     * @param params
     * @return
     */
    @Override
    public Object getTicketSlaDetail(TicketSLADTO params) {
        Optional<TicketSlaEntity> ticketSlaEntity = ticketSlaRepositoryJPA.findBySiteIdAndAndSourceIdAndAndTicketTypeIdAndStatus(params.getSiteId(), params.getSourceId(), params.getTicketTypeId(), TicketSlaEntity.Status.VALID.value);
        return ticketSlaEntity.orElse(null);
    }

    @Override
    public Object getTicketTypesTree(TicketTypeDTO params) {
        return ticketCategoryRepository.getTicketTypesTree(params);
    }

    @Override
    public Object saveTicketSla(TicketSLADTO ticketSLADTO, Authentication authentication) {
        ModelMapper modelMapper = new ModelMapper();
        TicketSlaEntity ticketSlaEntity = modelMapper.map(ticketSLADTO, TicketSlaEntity.class);
        if (!FnCommon.isNullObject(ticketSLADTO.getTicketSlaId())) {
            ticketSlaEntity.setUpdateDate(new Date(System.currentTimeMillis()));
            ticketSlaEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
        } else {
            ticketSlaEntity.setCreateDate(new Date(System.currentTimeMillis()));
            ticketSlaEntity.setCreateUser(FnCommon.getUserLogin(authentication));
            ticketSlaEntity.setStatus(TicketStatusEntity.TicketStatus.NEW.value);
        }
        return ticketSlaRepositoryJPA.save(ticketSlaEntity);
    }


    /**
     * Tinh sla theo giai phap moi
     *
     * @param priorityId
     * @param ticketTypeId
     * @param authentication
     * @return
     */
    @Override
    public java.util.Date getDateTicketSlaNew(Long priorityId, Long ticketTypeId, Authentication authentication, Long processTimeSla) {
        Optional<TicketSlaEntity> optionalTicketSlaEntity = ticketSlaRepositoryJPA.findByPriorityIdAndAndTicketTypeIdAndStatus(priorityId, ticketTypeId, TicketSlaEntity.Status.VALID.value);
        Long processTime = 0L;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(System.currentTimeMillis()));
        if (optionalTicketSlaEntity.isPresent()) {
            TicketSlaEntity ticketSlaEntity = optionalTicketSlaEntity.get();
            if (TicketSlaEntity.Unit.DAY.value.equals(ticketSlaEntity.getProcessTimeType())) {
                processTime = ticketSlaEntity.getProcessTime() * 24;
            } else if (TicketSlaEntity.Unit.HOUR.value.equals(ticketSlaEntity.getProcessTimeType())) {
                processTime = ticketSlaEntity.getProcessTime();
            }

            /* Kiem tra xem co cong thoi gian phoi hop hay khong*/
            if (ticketSlaEntity.getIsAddCombine() != null && ticketSlaEntity.getIsAddCombine() == 1L) {
                /* Them thoi gian xu ly phoi hop cap 1*/
                if (TicketSlaEntity.Unit.DAY.value.equals(ticketSlaEntity.getCombineTimeL1Type())) {
                    processTime += ticketSlaEntity.getProcessTime() * 24;
                } else if (TicketSlaEntity.Unit.HOUR.value.equals(ticketSlaEntity.getCombineTimeL1Type())) {
                    processTime += ticketSlaEntity.getProcessTime();
                }

                /* Them thoi gian xu ly phoi hop cap 2*/
                if (TicketSlaEntity.Unit.DAY.value.equals(ticketSlaEntity.getCombineTimeL1Type())) {
                    processTime += ticketSlaEntity.getProcessTime() * 24;
                } else if (TicketSlaEntity.Unit.HOUR.value.equals(ticketSlaEntity.getCombineTimeL1Type())) {
                    processTime += ticketSlaEntity.getProcessTime();
                }
            }

            /**
             * Tinh theo loai thoi gian xu ly
             * 0: không có giờ chết 24/24
             * 1: Làm tròn đến 24h ngày n + x với n là ngày tiếp nhận, x là thời hạn xử lý,
             * Với phản ánh có hạn đến 24h ngày chủ nhật(lễ tết) sẽ gia hạn đến 24h ngày thứ 2 tuần kế tiếp
             * 2: Trừ thời gian chết bao gồm cả từ 22h đêm đến 7h sáng hôm sau và các ngày chủ nhật, lễ tết
             */
            int year = calendar.get(Calendar.YEAR);
            if (TicketSlaEntity.ProcessType.ALL_HOUR.value.equals(ticketSlaEntity.getProcessType())) {
                calendar.add(Calendar.HOUR_OF_DAY, processTime.intValue());
                return calendar.getTime();
            } else if (TicketSlaEntity.ProcessType.ROUND_LAST_DAY.value.equals(ticketSlaEntity.getProcessType())) {
                calendar.add(Calendar.HOUR_OF_DAY, processTime.intValue());
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                int dayOff = workingDaysRepositoryJPA.countAllByYyyyAndIsLeave((long) year, 1L, new java.sql.Date(calendar.getTime().getTime()));
                if (dayOff > 0) {
                    calendar.add(Calendar.DATE, dayOff);
                }
                return calendar.getTime();
            } else if (TicketSlaEntity.ProcessType.WORKING_HOUR.value.equals(ticketSlaEntity.getProcessType())) {
                calendar.add(Calendar.HOUR_OF_DAY, processTime.intValue());
                int dayOff = workingDaysRepositoryJPA.countAllByYyyyAndIsLeave((long) year, 1L, new java.sql.Date(calendar.getTime().getTime()));
                if (dayOff > 0) {
                    calendar.add(Calendar.DATE, dayOff);
                }
                return calendar.getTime();
//                int totalProcess = processTime.intValue();
//                int hour = calendar.get(Calendar.HOUR_OF_DAY);
//                int minute = calendar.get(Calendar.MINUTE);
//                if (hour >= 7 && hour <= 22) {
//                    if (minute >= 30) hour += 1;
//                } else {
//                    hour = 7;
//                }
//                int partOfDay = 22 - hour;
//                if(partOfDay > totalProcess){
//
//                }
            }
        }
        return null;
    }

    /***
     * Lay danh sach nhan vien
     * @param params
     * @return
     */
    @Override
    public Object getTicketSiteUser(TicketSiteUserDTO params, Authentication authentication) {
        return ticketCategoryRepository.getTicketSiteUser(params, authentication);
    }

    /***
     * Lay danh sach ly do gia han
     * @param params
     * @return
     */
    @Override
    public Object getTicketExtentReason(TicketExtentReasonDTO params, Authentication authentication) {
        return ticketCategoryRepository.getTicketExtentReason(params, authentication);
    }
}
