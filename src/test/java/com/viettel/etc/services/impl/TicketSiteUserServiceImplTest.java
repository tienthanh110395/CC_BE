package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.repositories.TicketSiteUserRepository;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSiteUserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketSiteUserServiceImplTest {

    @Mock
    private TicketSiteUserRepositoryJPA mockTicketSiteUserRepositoryJPA;

    @Mock
    private TicketSiteUserRepository mockTicketSiteUserRepository;

    @InjectMocks
    private TicketSiteUserServiceImpl ticketSiteUserServiceImplUnderTest;

    @Test
    void testGetTicketSiteUserByUser() {
        // Setup
        final TicketSiteUserDTO itemParamsEntity = new TicketSiteUserDTO();
        itemParamsEntity.setTicketSiteUserId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setUserId("userId");
        itemParamsEntity.setUserName("userName");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");

        // Configure TicketSiteUserRepositoryJPA.findByUserName(...).
        final TicketSiteUserEntity ticketSiteUserEntity1 = new TicketSiteUserEntity();
        ticketSiteUserEntity1.setTicketSiteUserId(0L);
        ticketSiteUserEntity1.setSiteId(0L);
        ticketSiteUserEntity1.setUserId("userId");
        ticketSiteUserEntity1.setUserName("userName");
        ticketSiteUserEntity1.setStaffCode("staffCode");
        ticketSiteUserEntity1.setStaffName("staffName");
        ticketSiteUserEntity1.setStatus(0L);
        ticketSiteUserEntity1.setCreateUser("createUser");
        ticketSiteUserEntity1.setCreateDate(new Date(0L));
        ticketSiteUserEntity1.setUpdateUser("updateUser");
        when(mockTicketSiteUserRepositoryJPA.findByUserNameIgnoreCase("userName")).thenReturn(ticketSiteUserEntity1);

        // Run the test
        final Object result = ticketSiteUserServiceImplUnderTest.getOneTicketSiteUser(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }


    @Test
    void testGetOneTicketSiteUser() {
        // Setup
        final TicketSiteUserDTO itemParamsEntity = new TicketSiteUserDTO();
        itemParamsEntity.setTicketSiteUserId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setSiteName("siteName");
        itemParamsEntity.setUserId("userId");
        itemParamsEntity.setUserName("userName");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure TicketSiteUserRepositoryJPA.findByUserNameIgnoreCase(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        when(ticketSiteUserServiceImplUnderTest.ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase("userName")).thenReturn(ticketSiteUserEntity);

        // Run the test
        final Object result = ticketSiteUserServiceImplUnderTest.getOneTicketSiteUser(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketSiteUser() {
        // Setup
        final TicketSiteUserDTO itemParamsEntity = new TicketSiteUserDTO();
        itemParamsEntity.setTicketSiteUserId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setSiteName("siteName");
        itemParamsEntity.setUserId("userId");
        itemParamsEntity.setUserName("userName");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Run the test
        final Object result = mockTicketSiteUserRepository.getTicketSiteUser(itemParamsEntity, authentication);

        // Verify the results
    }
}
