package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class DTOTest {

    @BeforeEach
    void setUp() {
    }


    @Test
    void FileDTO() {
        assertThat(FileDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void SearchTicketDTO() {
        assertThat(SearchTicketDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketAdjustChargeDTO() {
        assertThat(TicketAdjustChargeDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketAssignDTO() {
        assertThat(TicketAssignDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketAssignProcessDTO() {
        assertThat(TicketAssignProcessDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketAssignProcessIdDTO() {
        assertThat(TicketAssignProcessIdDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketDTO() {
        assertThat(TicketDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketHistoryDTO() {
        assertThat(TicketHistoryDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketHistoryListDTO() {
        assertThat(TicketHistoryListDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketInfoDTO() {
        assertThat(TicketInfoDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketProcessDTO() {
        assertThat(TicketProcessDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketSiteDTO() {
        assertThat(TicketSiteDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketSLADTO() {
        assertThat(TicketSLADTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketTypeDTO() {
        assertThat(TicketTypeDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }


    @Test
    void TicketSiteUserDTO() {
        assertThat(TicketSiteUserDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void TicketStatusDTO() {
        assertThat(TicketStatusDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}
