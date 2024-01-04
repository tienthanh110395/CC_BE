package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Action_audit_detail
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:38 ICT 2021
 */
@Repository
public interface ActionAuditDetailRepositoryJPA extends JpaRepository<ActionAuditDetailEntity, Long> {

    List<ActionAuditDetailEntity> findAllByActionAuditId(Long actionAuditId);
}
