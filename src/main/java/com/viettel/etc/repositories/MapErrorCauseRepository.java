package com.viettel.etc.repositories;

import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author ThaiBQ
 * @date   07/06/2023
 */
public interface MapErrorCauseRepository {

    Object searchMapErrorCause(Authentication authentication, MapErrorCauseSearchDTO params);

    /**
     * Lấy danh sách data ở màn Cập nhât nguyên nhân lỗi
     *
     * @param ticketGenreId
     * @param authentication
     * @return
     */
    Object searchDataMapForUpdate(Long ticketGenreId, Authentication authentication);

    ResultSelectEntity exportImpact(MapErrorCauseSearchDTO params);

    Object getErrorCauseByParentIdForUpdateMap(Authentication authentication, MapErrorCauseSearchDTO dataParams);

}
