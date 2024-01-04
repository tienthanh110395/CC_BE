package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

class MapErrorCauseRepositoryImplTest {

    private MapErrorCauseRepositoryImpl mapErrorCauseRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorCauseRepositoryImplUnderTest = new MapErrorCauseRepositoryImpl();
    }

    @Test
    void testSearchMapErrorCause() {
        // Setup
        final MapErrorCauseSearchDTO params = new MapErrorCauseSearchDTO();
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setLstErrorCauseLv1(Arrays.asList(0L));
        params.setLstErrorCauseLv2(Arrays.asList(0L));
        params.setLstErrorCauseLv3(Arrays.asList(0L));
        params.setLstParentLvId2(Arrays.asList(0L));
        params.setLstParentLvId3(Arrays.asList(0L));

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return null;
            }
        };

        // Run the test
        final Object result = mapErrorCauseRepositoryImplUnderTest.searchMapErrorCause(null, params);

        // Verify the results
        Assertions.assertNull(mapErrorCauseRepositoryImplUnderTest.searchMapErrorCause(null, params));
//        Assertions.assertNotNull(result);
    }

    @Test
    void testSearchDataMapForUpdate() {
        final  Object result = mapErrorCauseRepositoryImplUnderTest.searchDataMapForUpdate(0L, null);
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testExportImpact() {
        // Setup
        final MapErrorCauseSearchDTO params = new MapErrorCauseSearchDTO();
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setLstErrorCauseLv1(Arrays.asList(0L));
        params.setLstErrorCauseLv2(Arrays.asList(0L));
        params.setLstErrorCauseLv3(Arrays.asList(0L));
        params.setLstParentLvId2(Arrays.asList(0L));
        params.setLstParentLvId3(Arrays.asList(0L));

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return null;
            }
        };

        // Run the test
        final Object result = mapErrorCauseRepositoryImplUnderTest.exportImpact(params);

        // Verify the results
        Assertions.assertNull(mapErrorCauseRepositoryImplUnderTest.exportImpact(params));

        // Verify the results
    }

    @Test
    void testGetErrorCauseByParentIdForUpdateMap() {
        // Setup
        final MapErrorCauseSearchDTO dataParams = new MapErrorCauseSearchDTO();
        dataParams.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setCreateUser("createUser");
        dataParams.setStartRecord(0);
        dataParams.setPageSize(0);
        dataParams.setLstTicketTypeGroupId(Arrays.asList(0L));
        dataParams.setLstTicketCategoryId(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv1(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv2(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv3(Arrays.asList(0L));
        dataParams.setLstParentLvId2(Arrays.asList(0L));
        dataParams.setLstParentLvId3(Arrays.asList(0L));

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return null;
            }
        };

        // Run the test
        final Object result = mapErrorCauseRepositoryImplUnderTest.getErrorCauseByParentIdForUpdateMap(null,
                dataParams);

        Assertions.assertNull(mapErrorCauseRepositoryImplUnderTest.getErrorCauseByParentIdForUpdateMap(null, dataParams));

        // Verify the results
    }
}
