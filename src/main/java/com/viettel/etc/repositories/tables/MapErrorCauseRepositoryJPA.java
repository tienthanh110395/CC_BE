package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ThaiBQ
 * @date 08/06/2023
 */
@Repository
public interface MapErrorCauseRepositoryJPA extends JpaRepository<MapErrorEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = " DELETE FROM MAPPING_ERROR_CAUSE mec WHERE mec.map_id IN ( :lstMapId )", nativeQuery = true)
    void deleteDataByListId(List<Long> lstMapId);
}
