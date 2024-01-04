package com.viettel.etc.services.impl;

import com.viettel.etc.utils.exceptions.EtcException;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import org.apache.commons.io.input.NullInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    @Mock
    private MinioClient mockMinioClient;

    @InjectMocks
    private FileServiceImpl fileServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(fileServiceImplUnderTest, "bucket", "bucket");
    }

    @Test
    void testUploadFile() throws Exception {
        // Setup
        when(mockMinioClient.bucketExists(any(BucketExistsArgs.class))).thenReturn(false);
        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket","crm");
        // Configure MinioClient.putObject(...).
//        final ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(Headers.of("namesAndValues"), "bucket", "region", "object", "etag", "versionId");
//        when(mockMinioClient.putObject(any(PutObjectArgs.class))).thenReturn(objectWriteResponse);

        // Run the test
        fileServiceImplUnderTest.uploadFile("/filePath", "content".getBytes());
        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket",null);
        EtcException thrown = Assertions.assertThrows(EtcException.class,()->fileServiceImplUnderTest.uploadFile("/filePath", "content".getBytes()));
        Assertions.assertTrue(thrown.getMessage().contains("crm.file.upload.error"));
    }


    @Test
    void testRemoveFile() throws Exception {
        // Setup
        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket","crm");
        // Run the test
        fileServiceImplUnderTest.removeFile("/filePath");

        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket",null);
        EtcException thrown = Assertions.assertThrows(EtcException.class,()->fileServiceImplUnderTest.removeFile("/filePath"));
        Assertions.assertTrue(thrown.getMessage().contains("crm.file.remove.error"));
    }



    @Test
    void testGetFile() throws Exception {
        // Setup
        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket","crm");
        // Configure MinioClient.getObject(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockMinioClient.getObject(any(GetObjectArgs.class))).thenReturn(spyInputStream);

        // Run the test
        final byte[] result = fileServiceImplUnderTest.getFile("filePath");
        Assertions.assertNotNull(result);
        ReflectionTestUtils.setField(fileServiceImplUnderTest,"bucket",null);
        EtcException thrown = Assertions.assertThrows(EtcException.class,()->fileServiceImplUnderTest.getFile("/filePath"));
        Assertions.assertTrue(thrown.getMessage().contains("crm.file.get.error"));

    }

    @Test
    void testGetFile_MinioClientReturnsNoContent() throws Exception {
        // Setup
        // Configure MinioClient.getObject(...).
        final InputStream spyInputStream = spy(new NullInputStream());
        when(mockMinioClient.getObject(any())).thenReturn(spyInputStream);

        // Run the test
        final byte[] result = fileServiceImplUnderTest.getFile("filePath");

        // Verify the results
    }

    @Test
    void testGetRootFolder1() {
        assertThat(fileServiceImplUnderTest.getRootFolder()).isEqualTo(new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));
    }
}
