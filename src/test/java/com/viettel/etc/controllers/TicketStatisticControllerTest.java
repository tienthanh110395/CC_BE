package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.services.TicketStatisticService;
import com.viettel.etc.utils.FnCommon;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TicketStatisticControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketStatisticService mockTicketStatisticService;

    @InjectMocks
    private TicketStatisticController ticketStatisticController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketStatisticController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketStatistic() throws Exception {
        // Setup
        when(mockTicketStatisticService.getTicketStatistic(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-statistic")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicket() throws Exception {
        // Setup
        TicketStatisticDTO ticketStatisticDTO = new TicketStatisticDTO();
        when(mockTicketStatisticService.exportTicketStatistic(any(), any())).thenReturn("jenkinsfile_CD.groovy");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-statistic/exports")
                .content((new ObjectMapper().writeValueAsString(ticketStatisticDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testInsertTicketStatistic() throws Exception {
        // Setup
        TicketStatisticDTO ticketStatisticDTO = new TicketStatisticDTO();
        when(mockTicketStatisticService.insertTicketStatistic(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-statistic")
                .content((new ObjectMapper()).writeValueAsString(ticketStatisticDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
