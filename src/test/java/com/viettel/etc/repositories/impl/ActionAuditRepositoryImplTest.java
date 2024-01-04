package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

class ActionAuditRepositoryImplTest {

    private ActionAuditRepositoryImpl actionAuditRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditRepositoryImplUnderTest = new ActionAuditRepositoryImpl();
    }

    @Test
    void testGetActionAudit() {
        // Setup
        final ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActionAuditId(0L);
        actionAuditDTO.setActTypeId(0L);
        actionAuditDTO.setContractId(0L);
        actionAuditDTO.setTicketId(0L);
        actionAuditDTO.setTicketAssignId(0L);
        actionAuditDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDTO.setActionUserFullName("actionUserFullName");
        actionAuditDTO.setActionUserName("actionUserName");
        actionAuditDTO.setAppId("appId");
        actionAuditDTO.setIpPc("ipPc");

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final Object result = actionAuditRepositoryImplUnderTest.getActionAudit(actionAuditDTO);

        // Verify the results
    }
}
