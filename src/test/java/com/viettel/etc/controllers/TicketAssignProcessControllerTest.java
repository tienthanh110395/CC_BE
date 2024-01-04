package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketAssignProcessDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.services.TicketAssignProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TicketAssignProcessControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TicketAssignProcessService mockTicketAssignProcessService;
    @InjectMocks
    private TicketAssignProcessController ticketAssignProcessController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketAssignProcessController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testSaveResultProcess() throws Exception {
        // Setup
        TicketAssignProcessDTO ticketAssignProcessDTO =  new TicketAssignProcessDTO();
        when(mockTicketAssignProcessService.saveTicketAssignProcess(any(),any())).thenReturn(ticketAssignProcessDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+"/ticket-assign-processes")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignProcessDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void testGetTicketAssignProcess() throws Exception {
        // Setup
        TicketStatusDTO ticketStatusDTO =  new TicketStatusDTO();
        when(mockTicketAssignProcessService.getTicketAssignProcess(any(), any())).thenReturn(ticketStatusDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+"/ticket-assign-processes")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketAssignProcess() throws Exception {
        // Setup
        TicketAssignProcessDTO ticketAssignProcessDTO =  new TicketAssignProcessDTO();
        when(mockTicketAssignProcessService.saveTicketAssignProcess(new TicketAssignProcessDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-assign-processes")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignProcessDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
