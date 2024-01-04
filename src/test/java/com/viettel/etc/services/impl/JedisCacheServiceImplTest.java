package com.viettel.etc.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JedisCacheServiceImplTest {

    private JedisCacheServiceImpl jedisCacheServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        jedisCacheServiceImplUnderTest = new JedisCacheServiceImpl();
        jedisCacheServiceImplUnderTest.redisTemplateCluster = mock(RedisTemplate.class);
    }

    @Test
    void testGetMessageErrorByKey() {
        // Setup
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);

        // Run the test
        final String result = jedisCacheServiceImplUnderTest.getMessageErrorByKey("key");

        // Verify the results
    }

    @Test
    void testGetCodeErrorByKey() {
        // Setup
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);

        // Run the test
        final int result = jedisCacheServiceImplUnderTest.getCodeErrorByKey("key");

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testReplaceMessWithValues() {
        // Setup
        final Map<String, String> parameter = new HashMap<>();
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);

        // Run the test
        final String result = jedisCacheServiceImplUnderTest.replaceMessWithValues("mess", parameter);

        // Verify the results
    }

    @Test
    void testHdelete() {
        // Setup
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);
    }

    @Test
    void testHget() {
        // Setup
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);
    }

    @Test
    void testHset() {
        // Setup
        when(jedisCacheServiceImplUnderTest.redisTemplateCluster.opsForHash()).thenReturn(null);
    }
}
