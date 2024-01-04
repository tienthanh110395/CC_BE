package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.repositories.tables.entities.NotifyConfigEntity;
import com.viettel.etc.services.NotifyConfigService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static com.viettel.etc.utils.Constants.REQUEST_MAPPING_V1;

@ExtendWith(SpringExtension.class)
class NotifyConfigControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NotifyConfigService mockNotifyConfigService;

    @InjectMocks
    private NotifyConfigControllerTest notifyConfigControllerTest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notifyConfigControllerTest).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testGetNotificationConfigByType() throws Exception {
        // Setup
        when(mockNotifyConfigService.getNotificationConfigByType(0L)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                get(REQUEST_MAPPING_V1 + "/get-notification-config-by-type/{type}", 0)
                        .with(user("username"))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveNotifyConfig() throws Exception {
        // Setup
        List<NotifyConfigEntity> notifyConfigEntityLst = new ArrayList<>();
        when(mockNotifyConfigService.saveListNotifyConfig(Arrays.asList(new NotifyConfigEntity()), null))
                .thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(REQUEST_MAPPING_V1+ "/save-notify-config")
                .with(user("username"))
                .content((new ObjectMapper()).writeValueAsString(notifyConfigEntityLst))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
