package com.viettel.etc.services;

public interface FileService {
    void uploadFile(String filePath, byte[] file);

    void removeFile(String filePath);

    byte[] getFile(String filePath);

    String getRootFolder();

    String getRootFolder(String contractNo, String key);
}
