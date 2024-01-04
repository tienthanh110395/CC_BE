package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.TicketTypeRepository;
import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.tables.TicketTypeServiceJPA;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketTypeServiceImplTest {

    @Mock
    private TicketTypeRepository mockTicketTypeRepository;
    @Mock
    private TicketTypeServiceJPA mockTicketTypeServiceJPA;
    @Mock
    private TicketTypeRepositoryJPA mockTicketTypeRepositoryJPA;

    @InjectMocks
    private TicketTypeServiceImpl ticketTypeServiceImplUnderTest;

    @Test
    void testGetTicketType() {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final TicketTypeDTO itemParamsEntity = new TicketTypeDTO(ticketTypeEntity);
        final Authentication authentication = null;

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final Object result = ticketTypeServiceImplUnderTest.getTicketType(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketTypeDetails() {
        // Setup
        // Configure TicketTypeServiceJPA.getOne(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final Object result = ticketTypeServiceImplUnderTest.getTicketTypeDetails(0L);

        // Verify the results
    }

    @Test
    void testCreateTicketType() {
        // Setup
        final TicketTypeDTO dataParams = new TicketTypeDTO();
        dataParams.setTicketTypeId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId("0");
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketTypeServiceImplUnderTest.ticketTypeServiceJPA.save(any())).thenReturn(ticketTypeEntity);

        // Run the test
        final Object result = ticketTypeServiceImplUnderTest.createTicketType(dataParams, null);

        // Verify the results
    }

    @Test
    void testUpdateTicketType() {
        // Setup
        final TicketTypeDTO dataParams = new TicketTypeDTO();
        dataParams.setTicketTypeId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId("0");
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketTypeServiceJPA.getOne(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode("code");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("createUser");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketTypeServiceImplUnderTest.ticketTypeServiceJPA.getOne(0L)).thenReturn(ticketTypeEntity1);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode("code");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("createUser");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketTypeServiceImplUnderTest.ticketTypeServiceJPA.save(any())).thenReturn(ticketTypeEntity2);

        // Run the test
        final Object result = ticketTypeServiceImplUnderTest.updateTicketType(dataParams, 0L, null);

        // Verify the results
    }

    @Test
    void testRemoveTicketType() {
        // Setup
        // Run the test
        final Boolean result = ticketTypeServiceImplUnderTest.removeTicketType(0L, null);

        // Verify the results
        assertThat(result).isTrue();
        verify(mockTicketTypeRepositoryJPA).deleteById(0L);
    }

    @Test
    void testUpdateTicketType_ThrowsEtcException() {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final TicketTypeDTO dataParams = new TicketTypeDTO(ticketTypeEntity);

        // Configure TicketTypeServiceJPA.getOne(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode("code");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("createUser");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Mockito.lenient().when(mockTicketTypeServiceJPA.getOne(0L)).thenReturn(ticketTypeEntity1);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode("code");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("createUser");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Mockito.lenient().when(mockTicketTypeServiceJPA.save(new TicketTypeEntity())).thenReturn(ticketTypeEntity2);

        // Run the test
    }
}
