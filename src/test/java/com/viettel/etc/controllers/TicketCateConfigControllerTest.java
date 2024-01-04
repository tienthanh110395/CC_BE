package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.services.TicketCateConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class TicketCateConfigControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketCateConfigService mockTicketCateConfigService;

    @InjectMocks
    private TicketCateConfigController ticketCateConfigController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketCateConfigController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }


    @Test
    void testSaveOrUpdate() throws Exception {
        // Setup
        TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        when(mockTicketCateConfigService.createOrUpdate(new TicketCateConfigDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/ticket-cate-config/save-or-update")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveListTicketGroup() throws Exception {
        // Setup
        List<TicketCateConfigDTO> ticketCateConfigLst = new ArrayList<>();
        when(mockTicketCateConfigService.createUpdateList(Arrays.asList(new TicketCateConfigDTO()), null))
                .thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/ticket-cate-config/save-list-ticket-group")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigLst))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveListTicketGroup_TicketCateConfigServiceThrowsException() throws Exception {
        // Setup
        List<TicketCateConfigDTO> ticketCateConfigLst = new ArrayList<>();
        when(mockTicketCateConfigService.createUpdateList(Arrays.asList(new TicketCateConfigDTO()), null))
                .thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-cate-config/save-list-ticket-group")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigLst))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testOnDelete() throws Exception {
        // Setup
        when(mockTicketCateConfigService.onDelete(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete(REQUEST_MAPPING_V1+"/ticket-cate-config/delete/{ticketTypeId}", 0)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetDataDetail() throws Exception {
        // Setup
        when(mockTicketCateConfigService.getDataDetail(0L, new TicketCateConfigDTO())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                get(REQUEST_MAPPING_V1 + "/ticket-cate-config/data-detail/{ticketTypeId}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDoSearch() throws Exception {
        // Setup
        TicketConfigSearchDTO ticketConfigSearchDTO = new TicketConfigSearchDTO();
        when(mockTicketCateConfigService.searchTicketType(null, new TicketConfigSearchDTO())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/ticket-cate-config/search-data")
                .content((new ObjectMapper()).writeValueAsString(ticketConfigSearchDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testChangeStatus() throws Exception {
        // Setup
        TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        when(mockTicketCateConfigService.changeStatus(new TicketCateConfigDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/ticket-cate-config/change-status")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testChangeStatusMultiple() throws Exception {
        // Setup
        TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        when(mockTicketCateConfigService.updateStatusMultiple(new TicketCateConfigDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/ticket-cate-config/change-status-multiple")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketTypeByParentId() throws Exception {
        // Setup
        TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        when(mockTicketCateConfigService.getTicketTypeByParentId(new TicketCateConfigDTO(), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1 + "/ticket-cate-config/ticket-type-by-parent-id")
                .content((new ObjectMapper()).writeValueAsString(ticketCateConfigDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
