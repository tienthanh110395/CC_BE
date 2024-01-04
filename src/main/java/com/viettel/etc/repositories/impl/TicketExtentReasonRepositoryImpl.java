package com.viettel.etc.repositories.impl;
import com.viettel.etc.dto.TicketExtentReasonDTO;
import com.viettel.etc.repositories.TicketExtentReasonRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.HashMap;
/**
 * Autogen class Repository Impl: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:18 ICT 2022
 */
@Repository
public class TicketExtentReasonRepositoryImpl extends CommonDataBaseRepository implements TicketExtentReasonRepository{    
    /**
     * 
     * 
     * @param itemParamsEntity: params client truyen len
     * @return 
     */
    @Override
    public ResultSelectEntity getTicketExtentReason(TicketExtentReasonDTO itemParamsEntity){
         StringBuilder sql = new StringBuilder();
         sql.append("select * from TICKET_EXTENT_REASON");
         List<Object> arrParams = new ArrayList<>();
         //==========TODO: DEV Thuc hien bo sung params va edit query o day=====
         //Example: String sql = select * from table where column1=?,column2=?
         //         arrParams.add("value1");
         //         arrParams.add("value2");
         //==========END TODO ==================================================
         Integer start = null;
         if(itemParamsEntity!=null && itemParamsEntity.getStartrecord()!=null){
             start = itemParamsEntity.getStartrecord();
         }
         Integer pageSize = null;
         if(itemParamsEntity!=null && itemParamsEntity.getPagesize()!=null){
             pageSize = itemParamsEntity.getPagesize();
         }
         ResultSelectEntity resultData = getListDataAndCount(sql, arrParams, start, pageSize,TicketExtentReasonDTO.class);
         return resultData;
    }
}