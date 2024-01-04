package com.viettel.etc.services.impl;

import mockit.MockUp;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SMSServiceImplTest {

    private SMSServiceImpl smsServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        smsServiceImplUnderTest = new SMSServiceImpl();
    }

    @Test
    void testSendSMS() {
        // Setup
        final Authentication authentication = null;
        ReflectionTestUtils.setField(smsServiceImplUnderTest,"url","url");
        ReflectionTestUtils.setField(smsServiceImplUnderTest,"username","username");
        ReflectionTestUtils.setField(smsServiceImplUnderTest,"password","password");
        ReflectionTestUtils.setField(smsServiceImplUnderTest,"cpCode","cpCode");
        ReflectionTestUtils.setField(smsServiceImplUnderTest,"sender","sender");


        // Run the test
        new MockUp<HttpClient>(){
            @mockit.Mock
            public int executeMethod(HttpMethod method) throws IOException, HttpException{
                return 0;
            }
        };

        new MockUp<PostMethod>(){
            @mockit.Mock
            public InputStream getResponseBodyAsStream() throws IOException{
                return  new ByteArrayInputStream("<result>1</result>".getBytes());
            }
        };
        final int result = smsServiceImplUnderTest.sendSMS("phone", "content", authentication);

        // Verify the results
        assertThat(result).isEqualTo(0);
        int result1 = smsServiceImplUnderTest.sendSMS("84981651642", "content", authentication);
        assertThat(result1).isEqualTo(0);
        int result2 = smsServiceImplUnderTest.sendSMS("84981651642", "content", authentication);
        assertThat(result2).isEqualTo(0);
        new MockUp<HttpClient>(){
            @mockit.Mock
            public int executeMethod(HttpMethod method) throws IOException, HttpException{
                throw new HttpException();
            }
        };
        assertThat(smsServiceImplUnderTest.sendSMS("", "content", authentication)).isEqualTo(-1);
    }
}
