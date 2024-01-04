package com.viettel.etc.services;

import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Autogen class: Lớp thao tác map nguyên nhân lỗi
 *
 * @author ThaiBQ
 * @date 07/06/2023
 */
public interface MapErrorCauseService {

    Object searchMapErrorCause(Authentication authentication, MapErrorCauseSearchDTO params);

    Object saveMapError(List<MapErrorEntity> dataParams, Authentication authentication);

    Object onDelete(Long mapErrorCauseId, Authentication authentication);

    /**
     * Lấy danh sách data ở màn Cập nhât nguyên nhân lỗi
     * @param ticketGenreId
     * @param authentication
     * @return
     */
    Object searchDataMapForUpdate(Long ticketGenreId, Authentication authentication);

    void exportMapErrorCause(MapErrorCauseSearchDTO dataParams, HttpServletResponse response);

    void downloadMapErrorCauseTemplate(Authentication authentication, HttpServletResponse response);

    Object getErrorCauseByParentIdForUpdateMap(Authentication authentication, MapErrorCauseSearchDTO dataParams);

    Object updateMapError(List<MapErrorUpdateDTO> dataParams, Authentication authentication);
}
