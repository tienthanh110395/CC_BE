package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.MapErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ThaiBQ
 * @date 08/06/2023
 */
@Service
public class MapErrorCauseServiceJPA {

    @Autowired
    MapErrorCauseRepositoryJPA mapErrorCauseRepositoryJPA;

    public List<MapErrorEntity> saveAll(List<MapErrorEntity> dataList) {
        return this.mapErrorCauseRepositoryJPA.saveAll(dataList);
    }

    public MapErrorEntity getOne(Long id) {
        return this.mapErrorCauseRepositoryJPA.getOne(id);
    }

    public void deleteById(Long id) {
        this.mapErrorCauseRepositoryJPA.deleteById(id);
    }

    public void deleteDataByListId(List<Long> lstMapId) {
        this.mapErrorCauseRepositoryJPA.deleteDataByListId(lstMapId);
    }

    public Optional<MapErrorEntity> findById(Long id) {
        return this.mapErrorCauseRepositoryJPA.findById(id);
    }


}
