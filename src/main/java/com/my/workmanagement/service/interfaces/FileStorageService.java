package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    /**
     * 将文件保存到指定路径
     *
     * @param inputStream 输入流
     * @param path        路径
     * @param fileName    文件名
     * @param contentType 文件类型
     * @return 是否保存成功
     * @throws IOException
     */
    boolean store(InputStream inputStream, String path, String fileName, String contentType)
            throws IOException;

    boolean store(byte[] content, String path, String fileName, String contentType)
            throws IOException;

    boolean store(MultipartFile file, String path, String fileName) throws IOException;

    /**
     * 文件是否存在
     *
     * @param file 文件路径
     * @return true or false
     */
    boolean exists(String file);

    /**
     * 从指定的位置加载资源
     *
     * @param path 路径
     * @return 文件 Resource
     * @throws StorageFileNotFoundException 文件不存在
     */
    Resource loadAsResource(String path) throws StorageFileNotFoundException;
}
