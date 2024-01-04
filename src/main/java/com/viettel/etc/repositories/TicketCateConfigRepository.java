package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketCateConfigRepository {

    Object getDataDetail(Long ticketTypeId, TicketCateConfigDTO params);

//    Object searchImpactLog(Authentication authentication, TicketTypeLogDTO params);

    Object getDataDetailImpact(TicketTypeLogDetailDTO params);

    //TamDT
    Object searchTicketType(Authentication authentication, TicketConfigSearchDTO params);

    ResultSelectEntity getTicketTypeByParentId(TicketCateConfigDTO params);

    ResultSelectEntity exportImpactLog(TicketTypeLogDTO params);


    /**
     * Lấy danh sách thể loại phản ánh ở màn Tạo mới Map nguyên nhân lỗi
     *
     * @param parentId
     * @return
     */
    List<TicketCateConfigDTO> getTicketTypeByParentIdForMapping(Long parentId);

    List<TicketCateConfigDTO> getTicketTypeByParentIdForConfigTime(Long parentId);

    List<TicketCateConfigDTO> getTicketTypeByTicketTypeIdForConfigTime(Long ticketTypeId);

}
