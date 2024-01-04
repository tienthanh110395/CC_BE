package com.viettel.etc.repositories;
import com.viettel.etc.dto.TicketExtentReasonDTO;
import java.util.List;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
/**
 * Autogen class Repository Interface: 
 * 
 * @author toolGen
 * @date Fri Jan 07 16:57:18 ICT 2022
 */
public interface TicketExtentReasonRepository {


    public ResultSelectEntity getTicketExtentReason(TicketExtentReasonDTO itemParamsEntity);
}