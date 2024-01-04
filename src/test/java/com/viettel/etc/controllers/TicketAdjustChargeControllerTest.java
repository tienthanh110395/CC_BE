package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketAdjustChargeDTO;
import com.viettel.etc.services.TicketAdjustChargeService;
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
class TicketAdjustChargeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketAdjustChargeService mockTicketAdjustChargeService;
    @InjectMocks
    private TicketAdjustChargeController ticketAdjustChargeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketAdjustChargeController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testAdjustCharge() throws Exception {
        // Setup
        TicketAdjustChargeDTO ticketAdjustChargeDTO = new TicketAdjustChargeDTO();
        when(mockTicketAdjustChargeService.saveTicketAdjustCharge(any(),any())).thenReturn(ticketAdjustChargeDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+"/adjust-charges")
                .content((new ObjectMapper()).writeValueAsString(ticketAdjustChargeDTO)).contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketAdjustInfo() throws Exception {
        // Setup
        when(mockTicketAdjustChargeService.getTicketAdjustCharge(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1+"/adjust-charges/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketAdjustCharge() throws Exception {
        // Setup
        when(mockTicketAdjustChargeService.getTicketAdjustCharge(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/adjust-charges/0", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketAdjustCharge() throws Exception {
        // Setup
        TicketAdjustChargeDTO ticketAdjustChargeDTO = new TicketAdjustChargeDTO();
        when(mockTicketAdjustChargeService.saveTicketAdjustCharge(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/adjust-charges")
                .content((new ObjectMapper()).writeValueAsString(ticketAdjustChargeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
