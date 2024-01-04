package com.viettel.etc.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

class ContentNegotiationConfigurationTest {

    private ContentNegotiationConfiguration contentNegotiationConfigurationUnderTest;

    @BeforeEach
    void setUp() {
        contentNegotiationConfigurationUnderTest = new ContentNegotiationConfiguration();
    }

    @Test
    void testConfigureContentNegotiation() {
        // Setup
        final ContentNegotiationConfigurer configurer = new ContentNegotiationConfigurer(null);

        // Run the test
        contentNegotiationConfigurationUnderTest.configureContentNegotiation(configurer);

        // Verify the results
    }
}
