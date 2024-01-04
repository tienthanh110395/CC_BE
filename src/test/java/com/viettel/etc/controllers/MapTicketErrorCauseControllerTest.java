package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.services.MapErrorCauseService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
class MapTicketErrorCauseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MapErrorCauseService mockMapErrorCauseService;

    @Mock
    private TicketCateConfigService mockTicketCateConfigService;

    @InjectMocks
    private MapTicketErrorCauseController mapTicketErrorCauseController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mapTicketErrorCauseController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testDoSearch() throws Exception {
        // Setup
        MapErrorCauseSearchDTO mapErrorCauseSearchDTO = new MapErrorCauseSearchDTO();
        when(mockMapErrorCauseService.searchMapErrorCause(null, new MapErrorCauseSearchDTO())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/search-data-map-error-cause")
                .content((new ObjectMapper()).writeValueAsString(mapErrorCauseSearchDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveMapError() throws Exception {
        // Setup
        List<MapErrorEntity> mapErrorLst = new ArrayList<>();
        when(mockMapErrorCauseService.saveMapError(Arrays.asList(new MapErrorEntity()), null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/map-error-cause-create")
                .content((new ObjectMapper()).writeValueAsString(mapErrorLst))
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
        when(mockMapErrorCauseService.onDelete(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                delete(REQUEST_MAPPING_V1 + "/delete-map-error-cause/{mapErrorCauseId}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketTypeByParentIdForMap() throws Exception {
        // Setup
        // Configure TicketCateConfigService.getTicketTypeByParentIdForMapping(...).
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("ticketTypeName");
        ticketCateConfigDTO.setTicketTypeCode("ticketTypeCode");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setUpdateUser("updateUser");
        ticketCateConfigDTO.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setIsCpt(0L);
        ticketCateConfigDTO.setType(0L);
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setDeadTimeType(0L);
        final List<TicketCateConfigDTO> ticketCateConfigDTOS = Arrays.asList(ticketCateConfigDTO);
        when(mockTicketCateConfigService.getTicketTypeByParentIdForMapping(0L)).thenReturn(ticketCateConfigDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                get(REQUEST_MAPPING_V1 + "/ticket-type-by-parent-id-for-map/{parentId}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetTicketTypeByParentIdForMap_TicketCateConfigServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTicketCateConfigService.getTicketTypeByParentIdForMapping(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                get(REQUEST_MAPPING_V1+ "/ticket-type-by-parent-id-for-map/{parentId}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDoSearchForUpdate() throws Exception {
        // Setup
        when(mockMapErrorCauseService.searchDataMapForUpdate(0L, null)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post(REQUEST_MAPPING_V1+ "/search-data-update_map-error-cause/{ticketGenreId}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testExportMapErrorCause() throws Exception {
        // Setup
        // Run the test
        MapErrorCauseSearchDTO mapErrorCauseSearchDTO =  new MapErrorCauseSearchDTO();
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/map-error/exports")
                .content((new ObjectMapper()).writeValueAsString(mapErrorCauseSearchDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(mockMapErrorCauseService).exportMapErrorCause(eq(new MapErrorCauseSearchDTO()),
                any(HttpServletResponse.class));
    }

    @Test
    void testDownloadMapErrorCauseTemplate() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/map-error/download-template")
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(mockMapErrorCauseService).downloadMapErrorCauseTemplate(eq(null), any(HttpServletResponse.class));
    }

    @Test
    void testGetErrorCauseByParentIdForUpdateMap() throws Exception {
        // Setup
        MapErrorCauseSearchDTO mapErrorCauseSearchDTO = new MapErrorCauseSearchDTO();
        when(mockMapErrorCauseService.getErrorCauseByParentIdForUpdateMap(null,
                new MapErrorCauseSearchDTO())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post(REQUEST_MAPPING_V1 + "/error-cause-by-parent-id-for-update-map")
                        .content((new ObjectMapper()).writeValueAsString(mapErrorCauseSearchDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateMapError() throws Exception {
        // Setup
        List<MapErrorEntity> mapErrorLst = new ArrayList<>();
        when(mockMapErrorCauseService.updateMapError(Arrays.asList(new MapErrorUpdateDTO()), null))
                .thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/map-error-cause-update")
                .content((new ObjectMapper()).writeValueAsString(mapErrorLst))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("username"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
