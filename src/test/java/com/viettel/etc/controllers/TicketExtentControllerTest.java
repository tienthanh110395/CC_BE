package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.dto.TicketHistoryListDTO;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.services.TicketExtentService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import mockit.MockUp;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class TicketExtentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketExtentService mockTicketExtentService;

    @InjectMocks
    private TicketExtentController ticketExtentController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketExtentController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketExtent() throws Exception {
    }

    @Test
    void testInsertTicketExtent() throws Exception {
        // Setup
        TicketExtentDTO ticketExtentDTO = new TicketExtentDTO();
        when(mockTicketExtentService.insertTicketExtent(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-extents")
                .content((new ObjectMapper()).writeValueAsString(ticketExtentDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetDetailTicketExtent() throws Exception {
        // Setup
        when(mockTicketExtentService.getDetailTicketExtent(0L)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-extents/{ticketId}", 0)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateTicketExtent() throws Exception {
        // Setup
        TicketExtentDTO ticketExtentDTO = new TicketExtentDTO();
        ticketExtentDTO.setTicketId(0L);
        when(mockTicketExtentService.updateTicketExtent(any(), any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/ticket-extents/{ticketId}", 0)
                .content((new ObjectMapper()).writeValueAsString(ticketExtentDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testApproveTicketStatus() throws Exception {
        // Setup
        // Configure TicketExtentService.approveTicketStatus(...).
        TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setStatus(0L);
        when(mockTicketExtentService.approveTicketStatus(any(), any())).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/ticket-extents/approve")
                .content((new ObjectMapper()).writeValueAsString(ticketExtentEntity))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testApproveTicketStatus_TicketExtentServiceReturnsNoItems() throws Exception {
        // Setup
        TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        when(mockTicketExtentService.approveTicketStatus(any(), any())).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/ticket-extents/approve")
                .content((new ObjectMapper()).writeValueAsString(ticketExtentEntity))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRejectTicketStatus() throws Exception {
        // Setup
        TicketExtentDTO ticketExtentDTO = new TicketExtentDTO();
        ticketExtentDTO.setTicketId(0L);
        when(mockTicketExtentService.rejectTicketStatus(any(), any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1 + "/ticket-extents/{ticketId}/reject", 0)
                .content((new ObjectMapper()).writeValueAsString(ticketExtentDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicketExtent() throws Exception {
        // Setup
        TicketExtentDTO ticketExtentDTO = new TicketExtentDTO();
        ticketExtentDTO.setTicketId(0L);
        when(mockTicketExtentService.exportTicketExtent(any(), any())).thenReturn("jenkinsfile_CD.groovy");
        new MockUp<FnCommon>() {
            public void responseFile(HttpServletResponse response, String fileName) throws IOException {

            }
        };

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-extents/exports")
                .content((new ObjectMapper()).writeValueAsString(ticketExtentDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicketProcess() throws Exception {
        // Setup
        TicketExtentDTO ticketExtentDTO = new TicketExtentDTO();
        ticketExtentDTO.setTicketId(0L);
        when(mockTicketExtentService.exportTicketProcess(any(), any())).thenReturn("jenkinsfile_CD.groovy");
        new MockUp<FnCommon>() {
            public void responseFile(HttpServletResponse response, String fileName) throws IOException {

            }
        };

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-processes/exports")
                .content((new ObjectMapper()).writeValueAsString(ticketExtentDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
