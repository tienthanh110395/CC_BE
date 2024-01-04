package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.ActionAuditRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Action_audit
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:36 ICT 2021
 */
@Service
public class ActionAuditServiceJPA {

    @Autowired
    ActionAuditRepositoryJPA actionAuditRepositoryJPA;

    public List<ActionAuditEntity> findAll() {
        return this.actionAuditRepositoryJPA.findAll();
    }

    public ActionAuditEntity save(ActionAuditEntity actionAuditEntity) {
        return this.actionAuditRepositoryJPA.save(actionAuditEntity);
    }

    public Optional<ActionAuditEntity> findById(Long id) {
        return this.actionAuditRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.actionAuditRepositoryJPA.deleteById(id);
    }

    public ActionAuditEntity getOne(Long id) {
        return this.actionAuditRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.actionAuditRepositoryJPA.existsById(id);
    }


}
