package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.WorkingDaysDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

class WorkingDaysRepositoryImplTest {

    private WorkingDaysRepositoryImpl workingDaysRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workingDaysRepositoryImplUnderTest = new WorkingDaysRepositoryImpl();
    }

    @Test
    void testGetWorkingDays() {
        // Setup
        final WorkingDaysDTO itemParamsEntity = new WorkingDaysDTO();
        itemParamsEntity.setYyyy(0L);
        itemParamsEntity.setDd(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setType("type");
        itemParamsEntity.setIsLeave(0L);
        itemParamsEntity.setLeaveType(0L);
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);
        itemParamsEntity.setResultSqlEx(false);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = workingDaysRepositoryImplUnderTest.getWorkingDays(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
