package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.CcAllColumnHeaderRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.CcAllColumnHeaderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Cc_all_column_header
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:38 ICT 2021
 */
@Service
public class CcAllColumnHeaderServiceJPA {

    @Autowired
    CcAllColumnHeaderRepositoryJPA ccAllColumnHeaderRepositoryJPA;

    public List<CcAllColumnHeaderEntity> findAll() {
        return this.ccAllColumnHeaderRepositoryJPA.findAll();
    }

    public CcAllColumnHeaderEntity save(CcAllColumnHeaderEntity ccAllColumnHeaderEntity) {
        return this.ccAllColumnHeaderRepositoryJPA.save(ccAllColumnHeaderEntity);
    }

    public Optional<CcAllColumnHeaderEntity> findById(Long id) {
        return this.ccAllColumnHeaderRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ccAllColumnHeaderRepositoryJPA.deleteById(id);
    }

    public CcAllColumnHeaderEntity getOne(Long id) {
        return this.ccAllColumnHeaderRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ccAllColumnHeaderRepositoryJPA.existsById(id);
    }


}
