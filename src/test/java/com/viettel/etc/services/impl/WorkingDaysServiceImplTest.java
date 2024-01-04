package com.viettel.etc.services.impl;

import com.viettel.etc.dto.WorkingDaysDTO;
import com.viettel.etc.repositories.WorkingDaysRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WorkingDaysServiceImplTest {

    @Mock
    private WorkingDaysRepository mockWorkingDaysRepository;

    @InjectMocks
    private WorkingDaysServiceImpl workingDaysServiceImplUnderTest;

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

        // Configure WorkingDaysRepository.getWorkingDays(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockWorkingDaysRepository.getWorkingDays(new WorkingDaysDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = workingDaysServiceImplUnderTest.getWorkingDays(itemParamsEntity);

        // Verify the results
    }
}
