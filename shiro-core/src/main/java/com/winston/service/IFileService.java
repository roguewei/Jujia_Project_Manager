package com.winston.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    List<String> uploadFile(List<MultipartFile> multipartFiles, String describe, String title, String type);

    void delFile(String path);
}
