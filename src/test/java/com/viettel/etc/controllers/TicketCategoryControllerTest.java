package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketExtentReasonDTO;
import com.viettel.etc.dto.TicketSLADTO;
import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.TicketCategoryService;
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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TicketCategoryControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TicketCategoryService mockTicketCategoryService;
    @InjectMocks
    private TicketCategoryController ticketCategoryController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketCategoryController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetTicketSource() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketSource(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-sources")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketType() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketTypes(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-types")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketSiteByParentId() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketSiteByParentId(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-sites")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketSla() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketSla(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-slas")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketSlaDetail() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketSlaDetail(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-slas/1/1/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetDateTicketSlaNew() throws Exception {
        // Setup
        // Configure TicketCategoryService.getDateTicketSlaNew(...).
        final Date date = new Date();
        when(mockTicketCategoryService.getDateTicketSlaNew(any(), any(), any(), any())).thenReturn(date);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-slas-new")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketExtentReason() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketExtentReason(new TicketExtentReasonDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-extent-reasons")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketSiteUser() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketSiteUser(new TicketSiteUserDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-sites-user")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketTypeTree() throws Exception {
        // Setup
        when(mockTicketCategoryService.getTicketTypesTree(any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/ticket-types/tree")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveTicketSla() throws Exception {
        // Setup
        TicketSLADTO ticketSLADTO = new TicketSLADTO();
        Mockito.lenient().when(mockTicketCategoryService.saveTicketSla(new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, false), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-slas")
                .content((new ObjectMapper()).writeValueAsString(ticketSLADTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
