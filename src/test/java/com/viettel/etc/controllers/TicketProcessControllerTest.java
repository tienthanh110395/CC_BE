package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.services.impl.TicketProcessServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
class TicketProcessControllerTest {
    private MockMvc mvc;
    @Mock
    private TicketProcessServiceImpl mockTicketProcessService;
    @InjectMocks
    TicketProcessController ticketProcessController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(ticketProcessController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testSaveProcessOfCustCare() throws Exception {
        // Setup
        TicketProcessDTO ticketProcessDTO = new TicketProcessDTO();
        ticketProcessDTO.setTicketProcessId(1L);
        ticketProcessDTO.setStatus(2L);

        when(mockTicketProcessService.saveTicketProcess(any(),any())).thenReturn(ticketProcessDTO);

        // Run the test
        final MockHttpServletResponse response = mvc.perform(post(REQUEST_MAPPING_V1+"/ticket-processes")
                .content((new ObjectMapper()).writeValueAsString(ticketProcessDTO))
                .contentType(MediaType.APPLICATION_JSON)
//                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetTicketprocessInfo() throws Exception {
        // Setup
        TicketProcessDTO ticketProcessDTO = new TicketProcessDTO();
        ticketProcessDTO.setTicketId(1L);
        when(mockTicketProcessService.getTicketProcessDetails(any(),any())).thenReturn(ticketProcessDTO);

        // Run the test
        final MockHttpServletResponse response = mvc.perform(get(REQUEST_MAPPING_V1+"/ticket-processes/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(ticketProcessDTO);
    }

    @Test
    void testGetTicketProcessDetails() throws Exception {
        // Setup
        Mockito.lenient().when(mockTicketProcessService.getTicketProcessDetails(new TicketProcessDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-processes/0", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketProcess() throws Exception {
        // Setup
        TicketProcessDTO ticketProcessDTO = new TicketProcessDTO();
        when(mockTicketProcessService.saveTicketProcess(new TicketProcessDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-processes")
                .content((new ObjectMapper().writeValueAsString(ticketProcessDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
