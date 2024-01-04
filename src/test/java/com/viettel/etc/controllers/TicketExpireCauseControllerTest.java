package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketExpireCauseDTO;
import com.viettel.etc.services.TicketExpireCauseService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class TicketExpireCauseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketExpireCauseService mockTicketExpireCauseService;

    @InjectMocks
    private TicketExpireCauseController ticketExpireCauseController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketExpireCauseController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketExpireCause() throws Exception {
        // Setup
        when(mockTicketExpireCauseService.getTicketExpireCause(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-expire-causes")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testCreateTicketExpireCause() throws Exception {
        // Setup
        TicketExpireCauseDTO ticketExpireCauseDTO = new TicketExpireCauseDTO();
        when(mockTicketExpireCauseService.createTicketExpireCause(new TicketExpireCauseDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/category-reason-expire")
                .content((new ObjectMapper()).writeValueAsString(ticketExpireCauseDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRemoveTicketErrorCause() throws Exception {
        // Setup
        when(mockTicketExpireCauseService.removeTicketExpireCause(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1 + "/category-reason-expire/{ticketExpireCauseId}", 0)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSearchTicketExpireCause() throws Exception {
        // Setup
        when(mockTicketExpireCauseService.searchTicketExpireCause(new TicketExpireCauseDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/category-reason-expire")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSearchTreeTicketExpireCause() throws Exception {
        // Setup
        when(mockTicketExpireCauseService.searchTreeTicketExpireCause(new TicketExpireCauseDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/category-reason-expire-tree")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateTicketExpireCause() throws Exception {
        // Setup
        TicketExpireCauseDTO ticketExpireCauseDTO = new TicketExpireCauseDTO();
        ticketExpireCauseDTO.setTicketExpireCauseId(0L);
        when(mockTicketExpireCauseService.updateTicketExpireCause(any(), any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/category-reason-expire/{ticketExpireCauseId}", 0)
                .content((new ObjectMapper()).writeValueAsString(ticketExpireCauseDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
