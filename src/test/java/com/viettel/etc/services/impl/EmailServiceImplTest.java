package com.viettel.etc.services.impl;

import com.viettel.etc.services.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private FileService mockFileService;

    @InjectMocks
    private EmailServiceImpl emailServiceImplUnderTest;

    @Test
    void testSendMail() {
        // Setup
        final Map<String, String> parameter = new HashMap<>();
        final Map<String, String> fileAttach = new HashMap<>();

        // Run the test
        final boolean result = emailServiceImplUnderTest.sendMail("subject", "mailReceive", "filePath", parameter, null, fileAttach);

        // Verify the results
    }
}
