package com.viettel.etc.services.impl;

import com.viettel.etc.services.FileService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.exceptions.EtcException;
import io.minio.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final String ROOT_FOLDER = "yyyyMMdd";

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    /***
     * Upload file len server
     * @param filePath
     * @param file
     */
    @Override
    public void uploadFile(String filePath, byte[] file) {
        try {
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
            createBucketIfNotExist(bucket, false);
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(filePath).stream(new ByteArrayInputStream(file), file.length, -1).build());
        } catch (Exception e) {
            LOG.error("HAS ERROR", e);
            throw new EtcException("crm.file.upload.error");
        }
    }

    /***
     * Xoa file da upload
     * @param filePath
     */
    @Override
    public void removeFile(String filePath) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucket).object(filePath).build());
        } catch (Exception e) {
            LOG.error("HAS ERROR", e);
            throw new EtcException("crm.file.remove.error");
        }
    }

    /***
     * get binary cua file da upload
     * @param filePath
     * @return
     */
    @Override
    public byte[] getFile(String filePath) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(filePath)
                        .build())) {
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            LOG.error("HAS ERROR", e);
            throw new EtcException("crm.file.get.error");
        }
    }

    @Override
    public String getRootFolder() {
        return new SimpleDateFormat(ROOT_FOLDER).format(new Date(System.currentTimeMillis()));
    }

    @Override
    public String getRootFolder(String contractNo, String key) {
        if (key.equals(Constants.MINIO_FOLDER_KEY.OTHER)) {
            return key + File.separator + new SimpleDateFormat(ROOT_FOLDER).format(new Date(System.currentTimeMillis()));
        } else {
            return contractNo.substring(contractNo.length() - 3) + File.separator + key;
        }
    }

    /***
     * Tao bucket upload file
     * @param bucketName
     * @param objectLock
     */
    private void createBucketIfNotExist(String bucketName, boolean objectLock) {
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(objectLock).build());
            }
        } catch (Exception e) {
            LOG.error("HAS ERROR", e);
            throw new EtcException("crm.file.bucket.error");
        }
    }
}
