package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.repositories.ActionAuditDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActionAuditDetailServiceImplTest {

    private ActionAuditDetailServiceImpl actionAuditDetailServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditDetailServiceImplUnderTest = new ActionAuditDetailServiceImpl();
        actionAuditDetailServiceImplUnderTest.actionAuditDetailRepository = mock(ActionAuditDetailRepository.class);
    }

    @Test
    void testGetActAuditDetail() {
        // Setup
        final ActionAuditDetailDTO actionAuditDetailDTO = new ActionAuditDetailDTO();
        actionAuditDetailDTO.setActionAuditDetailId(0L);
        actionAuditDetailDTO.setActionAuditId(0L);
        actionAuditDetailDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailDTO.setTableName("tableName");
        actionAuditDetailDTO.setPkId(0L);
        actionAuditDetailDTO.setColumnName("columnName");
        actionAuditDetailDTO.setOldValue("oldValue");
        actionAuditDetailDTO.setNewValue("newValue");
        actionAuditDetailDTO.setActionName("actionName");
        actionAuditDetailDTO.setStartrecord(0);

        when(actionAuditDetailServiceImplUnderTest.actionAuditDetailRepository.getActionAuditDetails(new ActionAuditDetailDTO())).thenReturn("result");

        // Run the test
        final Object result = actionAuditDetailServiceImplUnderTest.getActAuditDetail(null, actionAuditDetailDTO);

        // Verify the results
    }
}
