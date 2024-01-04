package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.services.TicketStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
class TicketStatusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketStatusService mockTicketStatusService;

    @InjectMocks
    private  TicketStatusController ticketStatusController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketStatusController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketStatusHistory() throws Exception {
        // Setup
        when(mockTicketStatusService.getTicketStatus(any(),any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+"/ticket-status")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
    }

    @Test
    void testGetTicketStatus() throws Exception {
        // Setup
        Mockito.lenient().when(mockTicketStatusService.getTicketStatus(new TicketStatusDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+ "/ticket-status")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
    }
}
