package com.viettel.etc.services.impl;
import com.viettel.etc.repositories.TicketExtentReasonRepository;
import com.viettel.etc.dto.TicketExtentReasonDTO;
import com.viettel.etc.services.TicketExtentReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:18 ICT 2022
 */
@Service
public class TicketExtentReasonServiceImpl implements TicketExtentReasonService{
    @Autowired 
    private TicketExtentReasonRepository ticketExtentReasonRepository;
    
    /**
     * 
     * 
     * @param itemParamsEntity params client
     * @return 
     */
    @Override
    public Object getTicketExtentReason(TicketExtentReasonDTO itemParamsEntity) {
        /*
        ==========================================================
        itemParamsEntity: params nguoi dung truyen len
        ==========================================================
        */
        ResultSelectEntity dataResult = ticketExtentReasonRepository.getTicketExtentReason(itemParamsEntity);
        /*
        ==========================================================
        TODO: (Code at here) Thuc hien luong nghiep vu chi tiet
        ==========================================================
        */
        return dataResult;
    }
}