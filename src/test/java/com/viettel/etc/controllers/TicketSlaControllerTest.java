package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketSLADTO;
import com.viettel.etc.services.TicketSlaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
class TicketSlaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketSlaService mockTicketSlaService;

    @InjectMocks
    private TicketSlaController ticketSlaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketSlaController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketSla() throws Exception {
        // Setup
        when(mockTicketSlaService.getTicketSla(new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, false))).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-sla")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketSlaDetail() throws Exception {
        // Setup
        when(mockTicketSlaService.getTicketSlaDetail(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-sla/{ticketTypeId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
