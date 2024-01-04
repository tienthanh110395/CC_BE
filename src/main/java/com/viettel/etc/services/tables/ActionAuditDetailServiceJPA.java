package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.ActionAuditDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Action_audit_detail
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:37 ICT 2021
 */
@Service
public class ActionAuditDetailServiceJPA {

    @Autowired
    ActionAuditDetailRepositoryJPA actionAuditDetailRepositoryJPA;

    public List<ActionAuditDetailEntity> findAll() {
        return this.actionAuditDetailRepositoryJPA.findAll();
    }

    public ActionAuditDetailEntity save(ActionAuditDetailEntity actionAuditDetailEntity) {
        return this.actionAuditDetailRepositoryJPA.save(actionAuditDetailEntity);
    }

    public List<ActionAuditDetailEntity> saveAll(Iterable<ActionAuditDetailEntity> actionAuditDetailList) {
        return actionAuditDetailRepositoryJPA.saveAll(actionAuditDetailList);
    }

    public Optional<ActionAuditDetailEntity> findById(Long id) {
        return this.actionAuditDetailRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.actionAuditDetailRepositoryJPA.deleteById(id);
    }

    public ActionAuditDetailEntity getOne(Long id) {
        return this.actionAuditDetailRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.actionAuditDetailRepositoryJPA.existsById(id);
    }

    public List<ActionAuditDetailEntity> findAllByActionAuditId(Long actionAuditId) {
        return actionAuditDetailRepositoryJPA.findAllByActionAuditId(actionAuditId);
    }
}
