package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

class ActionAuditDetailRepositoryImplTest {

    private ActionAuditDetailRepositoryImpl actionAuditDetailRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditDetailRepositoryImplUnderTest = new ActionAuditDetailRepositoryImpl();
    }

    @Test
    void testGetActionAuditDetails() {
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

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final Object result = actionAuditDetailRepositoryImplUnderTest.getActionAuditDetails(actionAuditDetailDTO);

        // Verify the results
    }
}
