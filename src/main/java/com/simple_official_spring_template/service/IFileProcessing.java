package com.simple_official_spring_template.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileProcessing {
    void processCsvFile(MultipartFile file) throws IOException;
}
