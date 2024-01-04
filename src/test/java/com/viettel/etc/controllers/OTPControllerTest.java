package com.viettel.etc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.OtpDTO;
import com.viettel.etc.services.OtpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class OTPControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OtpService mockOtpService;

    @InjectMocks
    private OTPController otpController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(otpController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void testRequestOTPContract() throws Exception {
        // Setup
        when(mockOtpService.requestOTPContract(any(), any())).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(REQUEST_MAPPING_V1 + "/contracts/request-otp")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
