package com.viettel.etc.services;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.dto.TicketTypeLogDetailDTO;
import com.viettel.etc.dto.TicketTypeLogDTO;
import com.viettel.etc.repositories.tables.entities.TicketEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface TicketCateConfigService {

    Object createOrUpdate(TicketCateConfigDTO params, Authentication authentication);

    Object onDelete(Long ticketTypeId, Authentication authentication);

    Object getDataDetail(Long ticketTypeId, TicketCateConfigDTO params);

    Object changeStatus(TicketCateConfigDTO params, Authentication authentication);

    Object createUpdateList(List<TicketCateConfigDTO> params, Authentication authentication) throws Exception;

    Object updateStatusMultiple(TicketCateConfigDTO params, Authentication authentication);

    //TamDT
    Object searchTicketType(Authentication authentication, TicketConfigSearchDTO params);

    Object getTicketTypeByParentId(TicketCateConfigDTO params, Authentication authentication);


    /**
     * Lấy danh sách thể loại phản ánh ở màn Tạo mới Map nguyên nhân lỗi
     *
     * @param parentId
     * @return
     */
    List<TicketCateConfigDTO> getTicketTypeByParentIdForMapping(Long parentId);

    List<TicketCateConfigDTO> getTicketTypeForMapConfigTime(Long parentId);
    List<TicketCateConfigDTO> getTicketTypeForConfigTimeDetail(Long ticketTypeId);
}
