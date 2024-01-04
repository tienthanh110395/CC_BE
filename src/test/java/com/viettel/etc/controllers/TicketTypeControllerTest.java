package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.TicketTypeService;
import com.viettel.etc.utils.exceptions.EtcException;
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

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class TicketTypeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketTypeService mockTicketTypeService;

    @InjectMocks
    private TicketTypeController ticketTypeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketTypeController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketType() throws Exception {
        // Setup
        when(mockTicketTypeService.getTicketType(new TicketTypeDTO(new TicketTypeEntity()), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets-type")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketTypeDetails() throws Exception {
        // Setup
        when(mockTicketTypeService.getTicketTypeDetails(0L)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-type/{ticketTypeId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testCreateTicketType() throws Exception {
        // Setup
        TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
        when(mockTicketTypeService.createTicketType(new TicketTypeDTO(new TicketTypeEntity()), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post( REQUEST_MAPPING_V1 + "/tickets-type")
                .content((new ObjectMapper()).writeValueAsString(ticketTypeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateTicketType() throws Exception {
        // Setup
        TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
        ticketTypeDTO.setTicketTypeId(0L);
        when(mockTicketTypeService.updateTicketType(new TicketTypeDTO(new TicketTypeEntity()), 0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/tickets-type/{ticketTypeId}", 0)
                .content((new ObjectMapper()).writeValueAsString(ticketTypeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRemoveTicketErrorCause() throws Exception {
        // Setup
        when(mockTicketTypeService.removeTicketType(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1 + "/tickets-type/{ticketTypeId}", 0)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
