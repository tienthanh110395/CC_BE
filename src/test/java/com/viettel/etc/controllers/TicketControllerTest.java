package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.SearchTicketDTO;
import com.viettel.etc.dto.TicketDTO;
import com.viettel.etc.dto.TicketHistoryListDTO;
import com.viettel.etc.dto.TicketInfoDTO;
import com.viettel.etc.services.TicketService;
import com.viettel.etc.utils.FnCommon;
import mockit.MockUp;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TicketService mockTicketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testAddNewTicket() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        when(mockTicketService.saveTicket(any(),any())).thenReturn(ticketDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets")
                .content((new ObjectMapper()).writeValueAsString(ticketDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketInfo() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        when(mockTicketService.getTicketDetails(any(), any())).thenReturn(ticketDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketHistory() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        when(mockTicketService.getTicketHistory(any())).thenReturn(ticketDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets/ticket-histories/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetListTicketHistories() throws Exception {
        // Setup
        TicketHistoryListDTO ticketHistoryListDTO = new TicketHistoryListDTO();
        ticketHistoryListDTO.setTicketId("1");
        when(mockTicketService.getListTicketHistories(any())).thenReturn(ticketHistoryListDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets/ticket-histories")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportHistoryTicket() throws Exception {
        // Setup
        TicketHistoryListDTO ticketHistoryListDTO = new TicketHistoryListDTO();
        ticketHistoryListDTO.setTicketId("1");
        when(mockTicketService.exportHistoryTicket(any())).thenReturn("jenkinsfile_CD.groovy");
        new MockUp<FnCommon>() {
            public void responseFile(HttpServletResponse response, String fileName) throws IOException {

            }
        };

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets/ticket-histories/exports")
                .content((new ObjectMapper()).writeValueAsString(ticketHistoryListDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSearchTicket() throws Exception {
        // Setup
        SearchTicketDTO searchTicketDTO = new SearchTicketDTO();
        when(mockTicketService.searchTicket(any())).thenReturn(searchTicketDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicket() throws Exception {
        // Setup
        SearchTicketDTO searchTicketDTO = new SearchTicketDTO();
        when(mockTicketService.exportTicket(new SearchTicketDTO(), null)).thenReturn("jenkinsfile_CD.groovy");
        new MockUp<FnCommon>() {
            public void responseFile(HttpServletResponse response, String fileName) throws IOException {

            }
        };

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets/exports")
                .content((new ObjectMapper()).writeValueAsString(searchTicketDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testEditTicket() throws Exception {
        // Setup
        TicketInfoDTO ticketInfoDTO = new TicketInfoDTO();
        when(mockTicketService.editTicket(new TicketInfoDTO(), 0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/ticket/0", 0)
                .content((new ObjectMapper()).writeValueAsString(ticketInfoDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicketNotAssign() throws Exception {
        // Setup
        SearchTicketDTO searchTicketDTO = new SearchTicketDTO();
        when(mockTicketService.exportTicketNotAssign(new SearchTicketDTO(), null)).thenReturn("jenkinsfile_CD.groovy");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets-not-assign/exports")
                .content((new ObjectMapper().writeValueAsString(searchTicketDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicketReportPerformance() throws Exception {
        // Setup
        SearchTicketDTO searchTicketDTO = new SearchTicketDTO();
        when(mockTicketService.exportTicketReportPerformance(new SearchTicketDTO(), null)).thenReturn("jenkinsfile_CD.groovy");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets-report-performmance/exports")
                .content((new ObjectMapper().writeValueAsString(searchTicketDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketDetails() throws Exception {
        // Setup
        when(mockTicketService.getTicketDetails(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets/0", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketForCPT() throws Exception {
        // Setup
        Mockito.lenient().when(mockTicketService.getTicketForCPT(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketNotAssign() throws Exception {
        // Setup
        when(mockTicketService.getTicketNotAssign(new SearchTicketDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets-not-assign")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketReportPerformmance() throws Exception {
        // Setup
        when(mockTicketService.getTicketReportPerformmance(new SearchTicketDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/tickets-report-performmance")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicket() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        when(mockTicketService.saveTicket(new TicketDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets")
                .content((new ObjectMapper().writeValueAsString(ticketDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketForCPT() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        Mockito.lenient().when(mockTicketService.saveTicketForCPT(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/tickets")
                .content((new ObjectMapper().writeValueAsString(ticketDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateTicket() throws Exception {
        // Setup
        TicketDTO ticketDTO = new TicketDTO();
        Mockito.lenient().when(mockTicketService.updateTicket(new TicketDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/tickets/0", 0)
                .content((new ObjectMapper().writeValueAsString(ticketDTO)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
