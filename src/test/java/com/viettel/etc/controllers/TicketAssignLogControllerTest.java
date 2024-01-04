package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.services.TicketAssignLogService;
import com.viettel.etc.utils.FnCommon;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class TicketAssignLogControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketAssignLogService mockTicketAssignLogService;
    @InjectMocks
    private TicketAssignLogController ticketAssignLogController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketAssignLogController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetAssignLogByTicketId() throws Exception {
        // Setup
        when(mockTicketAssignLogService.getTicketAssignLog(new TicketAssignLogDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-assign-logs")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAddNewAssignLog() throws Exception {
        // Setup
        TicketAssignLogDTO ticketAssignLogDTO = new TicketAssignLogDTO();
        ticketAssignLogDTO.setTicketId(1L);
        new MockUp<FnCommon>() {
            @mockit.Mock
            public String getUserLogin(Authentication authentication) {
                return "userLogin";
            }
        };
        when(mockTicketAssignLogService.saveTicketAssignLog(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-assign-logs")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignLogDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRemoveAssignLog() throws Exception {
        // Setup
        when(mockTicketAssignLogService.removeTicketAssignLog(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1 + "/ticket-assign-logs/1")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketAssignLog() throws Exception {
        // Setup
        when(mockTicketAssignLogService.getTicketAssignLog(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-assign-logs")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRemoveTicketAssignLog() throws Exception {
        // Setup
        when(mockTicketAssignLogService.removeTicketAssignLog(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1 + "/ticket-assign-logs/0", 0)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketAssignLog() throws Exception {
        // Setup
        TicketAssignLogDTO ticketAssignLogDTO = new TicketAssignLogDTO();
        when(mockTicketAssignLogService.saveTicketAssignLog(new TicketAssignLogDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-assign-logs")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignLogDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
