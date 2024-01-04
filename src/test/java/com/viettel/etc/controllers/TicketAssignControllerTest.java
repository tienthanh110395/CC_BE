package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketAdjustChargeDTO;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.services.TicketAssignService;
import com.viettel.etc.utils.FnCommon;
import mockit.MockUp;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class TicketAssignControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketAssignService mockTicketAssignService;
    @InjectMocks
    private TicketAssignController ticketAssignController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketAssignController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketAssigns() throws Exception {
        // Setup
        when(mockTicketAssignService.getTicketAssigns(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-assigns")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportTicketAssigns() throws Exception {
        // Setup
        TicketAssignDTO ticketAssignDTO = new TicketAssignDTO();
        when(mockTicketAssignService.exportTicketAssigns(any(), any())).thenReturn("jenkinsfile_CD.groovy");
        new MockUp<FnCommon>() {
            public void responseFile(HttpServletResponse response, String fileName) throws IOException {

            }
        };
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-assigns/exports")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    void testExportTicketAssigns_ThrowsIOException() throws Exception {
        // Setup
        when(mockTicketAssignService.exportTicketAssigns(new TicketAssignDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("basePath/ticket-assigns/exports")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testReceiveTicketToHandle() throws Exception {
        // Setup
        TicketAssignDTO ticketAssignDTO =  new TicketAssignDTO();
        when(mockTicketAssignService.receiveTicketToHandle(any(), any())).thenReturn(ticketAssignDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put(REQUEST_MAPPING_V1+"/ticket-assigns")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketAssignByTicketId() throws Exception {
        // Setup
        when(mockTicketAssignService.getTicketAssignByTicketId(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+"/ticket-assigns/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketAssignById() throws Exception {
        // Setup
        when(mockTicketAssignService.getTicketAssignById(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+"/tickets/1/ticket-assigns/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testAddNewTicketAssigns() throws Exception {
        // Setup
        TicketAssignDTO ticketAssignDTO = new TicketAssignDTO();

        when(mockTicketAssignService.saveTicketAssign(any(),any())).thenReturn(ticketAssignDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+"/ticket-assigns")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    void testremoveTicketAssignById() throws Exception {
        // Setup
        when(mockTicketAssignService.removeTicketAssignById(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1+"/ticket-assigns")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testRemoveTicketAssignById() throws Exception {
        // Setup
        when(mockTicketAssignService.removeTicketAssignById(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1 + "/ticket-assigns")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketAssign() throws Exception {
        // Setup
        TicketAssignDTO ticketAssignDTO = new TicketAssignDTO();
        when(mockTicketAssignService.saveTicketAssign(new TicketAssignDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-assigns")
                .content((new ObjectMapper()).writeValueAsString(ticketAssignDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
